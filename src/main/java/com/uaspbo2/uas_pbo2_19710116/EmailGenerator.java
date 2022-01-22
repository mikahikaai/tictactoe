/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uaspbo2.uas_pbo2_19710116;

import java.net.InetAddress;
import javax.mail.internet.InternetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author mikah
 */
public class EmailGenerator {

    static Connection conn = new Koneksi().getKoneksi();
    static Random rand = new Random();
    static List<String> nama = new ArrayList<String>();

    public static String data_pembeli() {
        try {
            String sql = "SELECT nama_lengkap FROM pembeli";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                nama.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmailGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nama.get(rand.nextInt(nama.size()-1));
    }

    public static void sendMail(String akunPenerima) throws MessagingException, UnknownHostException {
        String host = "smtp.gmail.com";
        //configure Mailtrap's SMTP server details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        String akunEmail = "mikahikaai100@gmail.com";
        String passEmail = "rtbcvyfhgnpozx";

        //create the Session object
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(akunEmail, passEmail);
            }
        });

        Message message = prepareMessage(session, akunEmail, akunPenerima);
        Transport.send(message);

    }

    private static Message prepareMessage(Session session, String akunEmail, String akunPenerima) throws UnknownHostException {
        try {
            String hostname = InetAddress.getLocalHost().getHostName();
            //create a MimeMessage object
            Message message = new MimeMessage(session);

            //set From email field
            message.setFrom(new InternetAddress(akunEmail));

            //set To email field
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(akunPenerima));

            //set email subject field
            message.setSubject("Dari Uas PBO 2 Semester 5 - " + hostname + " - " + System.getProperty("user.name"));

            //set the content of the email message
            DateFormat formatHari = new SimpleDateFormat("EEEE");
            DateFormat formatTanggal = new SimpleDateFormat("dd MMMM yyyy");
            DateFormat formatJam = new SimpleDateFormat("HH:mm:ss");
            DateFormat formatBag = new SimpleDateFormat("z");
            Date tanggal = new java.util.Date();
            message.setText("Hai " + data_pembeli() + ", kami baru saja melihat kamu membuka aplikasi " + HalamanUtama.title + " pada hari " + formatHari.format(tanggal)
                    + " tanggal " + formatTanggal.format(tanggal) + " jam " + formatJam.format(tanggal) + " " + formatBag.format(tanggal)
                    + "\nTerimakasih telah menggunakan aplikasi ini");

            return message;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
