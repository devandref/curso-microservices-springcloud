package io.github.devandref.ms_avaliador_credito.application.exception;

public class ErroComunicacaoMicroserviceException extends Exception {

    private Integer statusHttp;

    public ErroComunicacaoMicroserviceException(String msg, Integer statusHttp) {
        super(msg);
        this.statusHttp = statusHttp;
    }

    public Integer getStatusHttp() {
        return statusHttp;
    }

    public void setStatusHttp(Integer statusHttp) {
        this.statusHttp = statusHttp;
    }

}
