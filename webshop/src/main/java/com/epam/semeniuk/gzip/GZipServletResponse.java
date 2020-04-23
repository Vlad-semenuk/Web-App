package com.epam.semeniuk.gzip;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class GZipServletResponse extends HttpServletResponseWrapper {

    private GZipServletOutputStream gzipOutputStream = null;
    private PrintWriter printWriter = null;

    public GZipServletResponse(HttpServletResponse response) throws IOException {
        super(response);
    }

    public void close() throws IOException {

        if (this.printWriter != null) {
            this.printWriter.close();
        }

        if (this.gzipOutputStream != null) {
            this.gzipOutputStream.close();
        }
    }


    @Override
    public void flushBuffer() throws IOException {

        if (printWriter != null) {
            printWriter.flush();
        }

        if (gzipOutputStream != null) {
            gzipOutputStream.flush();
        }
        super.flushBuffer();

    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (printWriter != null) {
            throw new IllegalStateException("PrintWriter obtained already - cannot get OutputStream");
        }
        if (gzipOutputStream == null) {
            gzipOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
        }
        return gzipOutputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (printWriter == null && this.gzipOutputStream != null) {
            throw new IllegalStateException("OutputStream obtained already - cannot get PrintWriter");
        }
        if (printWriter == null) {
            gzipOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
            printWriter = new PrintWriter(new OutputStreamWriter(this.gzipOutputStream, getResponse().getCharacterEncoding()));
        }
        return printWriter;
    }

}
