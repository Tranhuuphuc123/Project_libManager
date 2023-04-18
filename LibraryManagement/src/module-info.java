module Quanlythuvien.Version02 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires mysql.connector.java;
    requires java.sql;
    requires com.jfoenix;
    requires java.base;
    requires java.net.http;

    exports testUI;
    opens controller;
    exports controller;

    opens model;
    exports model;
    opens view;

}