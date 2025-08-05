package lest.dev.CommerceMail.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEmail(String emailDestinatary, Long userId) {
        String link = "http://localhost:4200/reset-password/";
        link += userId;
        try {
            String htmlContent =
                    "<!DOCTYPE html>\n" +
                            "<html lang=\"pt-BR\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                            "    <title>Confirmação de Redefinição de Senha</title>\n" +
                            "    <style>\n" +
                            "        /* Reset CSS para compatibilidade com clientes de email */\n" +
                            "        body, table, td, p, a, li, blockquote {\n" +
                            "            -webkit-text-size-adjust: 100%;\n" +
                            "            -ms-text-size-adjust: 100%;\n" +
                            "        }\n" +
                            "        \n" +
                            "        table, td {\n" +
                            "            mso-table-lspace: 0pt;\n" +
                            "            mso-table-rspace: 0pt;\n" +
                            "        }\n" +
                            "        \n" +
                            "        img {\n" +
                            "            -ms-interpolation-mode: bicubic;\n" +
                            "            border: 0;\n" +
                            "            height: auto;\n" +
                            "            line-height: 100%;\n" +
                            "            outline: none;\n" +
                            "            text-decoration: none;\n" +
                            "        }\n" +
                            "        \n" +
                            "        /* Estilos principais */\n" +
                            "        body {\n" +
                            "            margin: 0;\n" +
                            "            padding: 0;\n" +
                            "            background-color: #f4f4f4;\n" +
                            "            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                            "            font-size: 16px;\n" +
                            "            line-height: 1.6;\n" +
                            "            color: #333333;\n" +
                            "        }\n" +
                            "        \n" +
                            "        .email-container {\n" +
                            "            max-width: 600px;\n" +
                            "            margin: 0 auto;\n" +
                            "            background-color: #ffffff;\n" +
                            "        }\n" +
                            "        \n" +
                            "        .header {\n" +
                            "            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);\n" +
                            "            padding: 40px 20px;\n" +
                            "            text-align: center;\n" +
                            "        }\n" +
                            "        \n" +
                            "        .header h1 {\n" +
                            "            color: #ffffff;\n" +
                            "            font-size: 28px;\n" +
                            "            font-weight: 600;\n" +
                            "            margin: 0;\n" +
                            "            text-shadow: 0 2px 4px rgba(0,0,0,0.1);\n" +
                            "        }\n" +
                            "        \n" +
                            "        .content {\n" +
                            "            padding: 40px 30px;\n" +
                            "            text-align: center;\n" +
                            "        }\n" +
                            "        \n" +
                            "        .welcome-text {\n" +
                            "            font-size: 18px;\n" +
                            "            color: #333333;\n" +
                            "            margin-bottom: 20px;\n" +
                            "            line-height: 1.6;\n" +
                            "        }\n" +
                            "        \n" +
                            "        .message-text {\n" +
                            "            font-size: 16px;\n" +
                            "            color: #666666;\n" +
                            "            margin-bottom: 30px;\n" +
                            "            line-height: 1.6;\n" +
                            "        }\n" +
                            "        \n" +
                            "        .button-container {\n" +
                            "            margin: 40px 0;\n" +
                            "        }\n" +
                            "        \n" +
                            "        .confirm-button {\n" +
                            "            display: inline-block;\n" +
                            "            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);\n" +
                            "            color: #ffffff;\n" +
                            "            text-decoration: none;\n" +
                            "            padding: 16px 32px;\n" +
                            "            border-radius: 50px;\n" +
                            "            font-size: 18px;\n" +
                            "            font-weight: 600;\n" +
                            "            text-align: center;\n" +
                            "            box-shadow: 0 4px 15px rgba(102, 126, 234, 0.3);\n" +
                            "            transition: all 0.3s ease;\n" +
                            "        }\n" +
                            "        \n" +
                            "        .confirm-button:hover {\n" +
                            "            transform: translateY(-2px);\n" +
                            "            box-shadow: 0 6px 20px rgba(102, 126, 234, 0.4);\n" +
                            "        }\n" +
                            "        \n" +
                            "        .security-notice {\n" +
                            "            background-color: #f8f9fa;\n" +
                            "            border-left: 4px solid #667eea;\n" +
                            "            padding: 20px;\n" +
                            "            margin: 30px 0;\n" +
                            "            text-align: left;\n" +
                            "        }\n" +
                            "        \n" +
                            "        .security-notice h3 {\n" +
                            "            color: #667eea;\n" +
                            "            font-size: 16px;\n" +
                            "            margin: 0 0 10px 0;\n" +
                            "        }\n" +
                            "        \n" +
                            "        .security-notice p {\n" +
                            "            color: #666666;\n" +
                            "            font-size: 14px;\n" +
                            "            margin: 0;\n" +
                            "            line-height: 1.5;\n" +
                            "        }\n" +
                            "        \n" +
                            "        .footer {\n" +
                            "            background-color: #f8f9fa;\n" +
                            "            padding: 30px 20px;\n" +
                            "            text-align: center;\n" +
                            "            border-top: 1px solid #e9ecef;\n" +
                            "        }\n" +
                            "        \n" +
                            "        .footer p {\n" +
                            "            color: #666666;\n" +
                            "            font-size: 14px;\n" +
                            "            margin: 5px 0;\n" +
                            "        }\n" +
                            "        \n" +
                            "        .logo {\n" +
                            "            width: 80px;\n" +
                            "            height: 80px;\n" +
                            "            background-color: #ffffff;\n" +
                            "            border-radius: 50%;\n" +
                            "            margin: 0 auto 20px;\n" +
                            "            display: flex;\n" +
                            "            align-items: center;\n" +
                            "            justify-content: center;\n" +
                            "            font-size: 32px;\n" +
                            "            color: #667eea;\n" +
                            "            font-weight: bold;\n" +
                            "        }\n" +
                            "        \n" +
                            "        .expiry-notice {\n" +
                            "            background-color: #fff3cd;\n" +
                            "            border: 1px solid #ffeaa7;\n" +
                            "            border-radius: 8px;\n" +
                            "            padding: 15px;\n" +
                            "            margin: 20px 0;\n" +
                            "            text-align: center;\n" +
                            "        }\n" +
                            "        \n" +
                            "        .expiry-notice p {\n" +
                            "            color: #856404;\n" +
                            "            font-size: 14px;\n" +
                            "            margin: 0;\n" +
                            "        }\n" +
                            "        \n" +
                            "        /* Responsividade para dispositivos móveis */\n" +
                            "        @media only screen and (max-width: 600px) {\n" +
                            "            .email-container {\n" +
                            "                width: 100% !important;\n" +
                            "            }\n" +
                            "            \n" +
                            "            .header {\n" +
                            "                padding: 30px 15px;\n" +
                            "            }\n" +
                            "            \n" +
                            "            .header h1 {\n" +
                            "                font-size: 24px;\n" +
                            "            }\n" +
                            "            \n" +
                            "            .content {\n" +
                            "                padding: 30px 20px;\n" +
                            "            }\n" +
                            "            \n" +
                            "            .welcome-text {\n" +
                            "                font-size: 16px;\n" +
                            "            }\n" +
                            "            \n" +
                            "            .message-text {\n" +
                            "                font-size: 14px;\n" +
                            "            }\n" +
                            "            \n" +
                            "            .confirm-button {\n" +
                            "                padding: 14px 28px;\n" +
                            "                font-size: 16px;\n" +
                            "            }\n" +
                            "        }\n" +
                            "        \n" +
                            "        /* Fallbacks para clientes de email que não suportam CSS moderno */\n" +
                            "        .fallback-button {\n" +
                            "            background-color: #667eea;\n" +
                            "            color: #ffffff;\n" +
                            "            text-decoration: none;\n" +
                            "            padding: 16px 32px;\n" +
                            "            border-radius: 50px;\n" +
                            "            font-size: 18px;\n" +
                            "            font-weight: 600;\n" +
                            "            display: inline-block;\n" +
                            "        }\n" +
                            "    </style>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "    <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n" +
                            "        <tr>\n" +
                            "            <td align=\"center\" style=\"background-color: #f4f4f4; padding: 20px 0;\">\n" +
                            "                <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"600\" class=\"email-container\">\n" +
                            "                    <!-- Header -->\n" +
                            "                    <tr>\n" +
                            "                        <td class=\"header\">\n" +
                            "                            <div class=\"logo\">CM</div>\n" +
                            "                            <h1>Redefinição de Senha</h1>\n" +
                            "                        </td>\n" +
                            "                    </tr>\n" +
                            "                    \n" +
                            "                    <!-- Content -->\n" +
                            "                    <tr>\n" +
                            "                        <td class=\"content\">\n" +
                            "                            <p class=\"welcome-text\">\n" +
                            "                                Olá! Recebemos uma solicitação para redefinir sua senha.\n" +
                            "                            </p>\n" +
                            "                            \n" +
                            "                            <p class=\"message-text\">\n" +
                            "                                Para confirmar a redefinição da sua senha, clique no botão abaixo. \n" +
                            "                                Esta ação irá permitir que você defina uma nova senha para sua conta.\n" +
                            "                            </p>\n" +
                            "                            \n" +
                            "                            <!-- Botão de Confirmação -->\n" +
                            "                            <div class=\"button-container\">\n" +
                            "                                <a href=\""+link+"\" class=\"confirm-button fallback-button\">\n" +
                            "                                    Confirmar Redefinição de Senha\n" +
                            "                                </a>\n" +
                            "                            </div>\n" +
                            "                            \n" +
                            "                            <!-- Aviso de Segurança -->\n" +
                            "                            <div class=\"security-notice\">\n" +
                            "                                <h3>\uD83D\uDD12 Informações de Segurança</h3>\n" +
                            "                                <p>\n" +
                            "                                    • Este link é válido por 24 horas<br>\n" +
                            "                                    • Se você não solicitou esta redefinição, ignore este email<br>\n" +
                            "                                    • Nunca compartilhe este link com outras pessoas<br>\n" +
                            "                                    • Sua senha atual permanecerá ativa até a confirmação\n" +
                            "                                </p>\n" +
                            "                            </div>\n" +
                            "                            \n" +
                            "                            <!-- Aviso de Expiração -->\n" +
                            "                            <div class=\"expiry-notice\">\n" +
                            "                                <p>\n" +
                            "                                    ⏰ Este link expira em 24 horas por motivos de segurança.\n" +
                            "                                </p>\n" +
                            "                            </div>\n" +
                            "                            \n" +
                            "                            <p class=\"message-text\">\n" +
                            "                                Se o botão não funcionar, copie e cole o seguinte link no seu navegador:<br>\n" +
                            "                                <a href=\""+link+"\" style=\"color: #667eea; word-break: break-all;\">LINK_AQUI</a>\n" +
                            "                            </p>\n" +
                            "                        </td>\n" +
                            "                    </tr>\n" +
                            "                    \n" +
                            "                    <!-- Footer -->\n" +
                            "                    <tr>\n" +
                            "                        <td class=\"footer\">\n" +
                            "                            <p>Este email foi enviado automaticamente, não responda a esta mensagem.</p>\n" +
                            "                            <p>Se você tiver dúvidas, entre em contato conosco através do suporte.</p>\n" +
                            "                            <p style=\"margin-top: 20px; font-size: 12px; color: #999999;\">\n" +
                            "                                © 2024 CommerceMail. Todos os direitos reservados.\n" +
                            "                            </p>\n" +
                            "                        </td>\n" +
                            "                    </tr>\n" +
                            "                </table>\n" +
                            "            </td>\n" +
                            "        </tr>\n" +
                            "    </table>\n" +
                            "</body>\n" +
                            "</html>\n";
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8"); // 'true' para multi-part, 'UTF-8' para encoding

            helper.setFrom("leonardofigorellis@gmail.com"); // Pode ser configurado dinamicamente ou fixo
            helper.setTo(emailDestinatary);
            helper.setSubject("Redefinição de Senha");
            helper.setText(htmlContent, true); // 'true' indica que o conteúdo é HTML

            mailSender.send(message);
            System.out.println("Email HTML enviado com sucesso para: " + emailDestinatary);
            link = link.substring(0, link.lastIndexOf('/') + 1);
        } catch (MailException e) {
            System.err.println("Erro ao enviar email HTML para: " + emailDestinatary + " - " + e.getMessage());
            // Adicione tratamento de erro mais robusto aqui
        } catch (jakarta.mail.MessagingException e) { // Captura exceções específicas do JavaMail
            System.err.println("Erro de MimeMessage ao enviar email HTML para: " + emailDestinatary + " - " + e.getMessage());
        }
    }

}
