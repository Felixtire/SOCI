package com.projeto.soci.infra.configurations;


import com.projeto.soci.repository.UsuarioRepository;
import com.projeto.soci.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private static final Logger logger = Logger.getLogger(SecurityFilter.class.getName());

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        return path.equals("/login")
                || path.equals("/cadastrar")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var tokenJwt = recuperarToken(request);
        String uri = request.getRequestURI();
        String method = request.getMethod();

        try {
            if (tokenJwt != null) {
                logger.info("📋 Validando token para: " + method + " " + uri);

                // Valida e extrai o subject do token
                var subject = tokenService.getSubject(tokenJwt);
                logger.info("✅ Token validado para usuário: " + subject);

                // Busca o usuário no banco
                var usuario = usuarioRepository.findByEmail(subject)
                        .orElseThrow(() -> {
                            String msg = "❌ Usuário não encontrado no banco: " + subject;
                            logger.severe(msg);
                            return new RuntimeException(msg);
                        });

                // Configura a autenticação
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.info("✅ Autenticação configurada para: " + subject + " | " + method + " " + uri);
            } else {
                logger.fine("⚠️  Nenhum token fornecido em: " + method + " " + uri);
            }
        } catch (Exception e) {
            logger.severe("❌ Erro ao processar token: " + e.getMessage() + " | URI: " + uri);
            e.printStackTrace();
            // Limpa a autenticação em caso de erro
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "").trim();
        }
        return null;
    }
}
