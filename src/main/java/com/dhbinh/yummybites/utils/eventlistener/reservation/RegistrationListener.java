package com.dhbinh.yummybites.utils.eventlistener.reservation;

import com.dhbinh.yummybites.reservation.service.ReservationService;
import com.dhbinh.yummybites.reservation.service.dto.ReservationDTO;
import com.dhbinh.yummybites.reservation.service.mapper.ReservationMapper;
import com.dhbinh.yummybites.utils.Utils;
import net.bytebuddy.utility.RandomString;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class RegistrationListener {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationMapper reservationMapper;

    @Value("${verification.url}")
    private String verificationUrl;

    private static final String EMAIL_SUBJECT = "[YummyBites] Confirmation for Reservation";
    @Autowired
    private Utils utils;

    @EventListener
    public void onApplicationEvent(@NotNull OnReservationCompleteEvent event) throws MessagingException {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnReservationCompleteEvent event) throws MessagingException {
        ReservationDTO dto = event.getReservation();
        String token = UUID.randomUUID().toString();
        reservationService.createVerificationForReservation(reservationMapper.toEntity(dto), token);

        String message = emailTemplate();
        message = message.replace("[[URL]]", verificationUrl + token);
        message = message.replace("[[RandomString]]", Base64.getEncoder().encodeToString((verificationUrl + token).getBytes()));
        message = message.replace("[[name]]", dto.getName());

        Properties props = getProperties();

        Session session = Session.getInstance(props);

        MimeMessage email = new MimeMessage(session);
        email.setRecipients(MimeMessage.RecipientType.TO, (dto.getEmail()));
        email.setSubject(EMAIL_SUBJECT);
        email.setContent(message, "text/html");
        javaMailSender.send(email);
    }

    @NotNull
    private static Properties getProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "${spring.mail.properties.mail.smtp.auth}");
        props.put("mail.smtp.starttls.enable", "${spring.mail.properties.mail.smtp.starttls.enable}");
        props.put("mail.smtp.host", "${spring.mail.host}");
        props.put("mail.smtp.port", "${spring.mail.port}");
        return props;
    }

    private String emailTemplate() {
        return "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">\n" +
                "    <title>Email Confirmation</title>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <style type=\"text/css\">\n" +
                "        @media screen {\n" +
                "            @font-face {\n" +
                "                font-family: 'Source Sans Pro';\n" +
                "                font-style: normal;\n" +
                "                font-weight: 400;\n" +
                "                src: local('Source Sans Pro Regular'), local('SourceSansPro-Regular'), url(https://fonts.gstatic.com/s/sourcesanspro/v10/ODelI1aHBYDBqgeIAH2zlBM0YzuT7MdOe03otPbuUS0.woff) format('woff');\n" +
                "            }\n" +
                "            @font-face {\n" +
                "                font-family: 'Source Sans Pro';\n" +
                "                font-style: normal;\n" +
                "                font-weight: 700;\n" +
                "                src: local('Source Sans Pro Bold'), local('SourceSansPro-Bold'), url(https://fonts.gstatic.com/s/sourcesanspro/v10/toadOcfmlt9b38dHJxOBGFkQc6VGVFSmCnC_l7QZG60.woff) format('woff');\n" +
                "            }\n" +
                "        }\n" +
                "        body,\n" +
                "        table,\n" +
                "        td,\n" +
                "        a {\n" +
                "            -ms-text-size-adjust: 100%;\n" +
                "            -webkit-text-size-adjust: 100%;\n" +
                "        }\n" +
                "        table,\n" +
                "        td {\n" +
                "            mso-table-rspace: 0pt;\n" +
                "            mso-table-lspace: 0pt;\n" +
                "        }\n" +
                "        img {\n" +
                "            -ms-interpolation-mode: bicubic;\n" +
                "        }\n" +
                "        a[x-apple-data-detectors] {\n" +
                "            font-family: inherit !important;\n" +
                "            font-size: inherit !important;\n" +
                "            font-weight: inherit !important;\n" +
                "            line-height: inherit !important;\n" +
                "            color: inherit !important;\n" +
                "            text-decoration: none !important;\n" +
                "        }\n" +
                "        div[style*=\"margin: 16px 0;\"] {\n" +
                "            margin: 0 !important;\n" +
                "        }\n" +
                "        body {\n" +
                "            width: 100% !important;\n" +
                "            height: 100% !important;\n" +
                "            padding: 0 !important;\n" +
                "            margin: 0 !important;\n" +
                "        }\n" +
                "        table {\n" +
                "            border-collapse: collapse !important;\n" +
                "        }\n" +
                "        a {\n" +
                "            color: #1a82e2;\n" +
                "        }\n" +
                "        img {\n" +
                "            height: auto;\n" +
                "            line-height: 100%;\n" +
                "            text-decoration: none;\n" +
                "            border: 0;\n" +
                "            outline: none;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body style=\"background-color: #e9ecef;\">\n" +
                "    <div class=\"preheader\" style=\"display: none; max-width: 0; max-height: 0; overflow: hidden; font-size: 1px; line-height: 1px; color: #fff; opacity: 0;\">\n" +
                "    </div>\n" +
                "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "        <tr>\n" +
                "            <td style=\"height: 50px;\"></td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td align=\"center\" bgcolor=\"#e9ecef\">\n" +
                "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n" +
                "                    <tr>\n" +
                "                        <td align=\"left\" bgcolor=\"#ffffff\" style=\"padding: 36px 24px 0; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; border-top: 3px solid #d4dadf;\">\n" +
                "                            <h1 style=\"margin: 0; font-size: 32px; font-weight: 700; letter-spacing: -1px; line-height: 48px;\">Confirm Your Reservation</h1>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td align=\"center\" bgcolor=\"#e9ecef\">\n" +
                "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n" +
                "                    <tr>\n" +
                "                        <td align=\"left\" bgcolor=\"#ffffff\" style=\"padding: 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px;\">\n" +
                "                            <p><b>Dear [[name]],</b></p>\n" +
                "                            <p style=\"margin: 0;\">Click the button below to confirm your reservation.</p>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td align=\"left\" bgcolor=\"#ffffff\">\n" +
                "                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                <tr>\n" +
                "                                    <td align=\"center\" bgcolor=\"#ffffff\" style=\"padding: 12px 12px 12px 12px;\">\n" +
                "                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                                            <tr>\n" +
                "                                                <td align=\"center\" bgcolor=\"#1a82e2\" style=\"border-radius: 6px;\">\n" +
                "                                                    <a href=\"[[URL]]\" target=\"_blank\" style=\"display: inline-block; border: solid 16px #1a82e2; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; color: #ffffff; text-decoration: none; border-radius: 6px;\">Verify Your Reservation</a>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td align=\"left\" bgcolor=\"#ffffff\" style=\"padding: 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px;\">\n" +
                "                            <p style=\"margin: 0;\">If that doesn't work, click on this link or copy and paste it to your browser:</p>\n" +
                "                            <p style=\"margin: 0;\"><a href=\"[[URL]]\" target=\"_blank\">[[RandomString]]</a></p><br>\n" +
                "\n" +
                "                            <p style=\"margin: 0; font-size: 16px; color: #666;\"> If you didn't make a reservation, you can safely delete this email.</p>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td align=\"left\" bgcolor=\"#ffffff\" style=\"padding: 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; border-bottom: 3px solid #d4dadf\">\n" +
                "                            <p style=\"margin: 0;\">Best Regard,<br>YummyBites.</p>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td align=\"center\" bgcolor=\"#e9ecef\" style=\"padding: 10px;\">\n" +
                "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" bgcolor=\"#e9ecef\" style=\"padding: 12px 24px; font-family: 'Source Sans Pro', Helvetica, Arial, sans-serif; font-size: 14px; line-height: 20px; color: #666;\">\n" +
                "                            <p style=\"margin: 0;\">YummyBites</p>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</body>\n";
    }
}
