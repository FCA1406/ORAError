package br.com.decade.oraerror.server;


public class ORAErrorBO {
    private String code;
    private String erro;
    private String action;
    private String cause;
    private String favorite;

    public ORAErrorBO() {
        super();
    }
/*
    public ORAErrorBO(String code, String erro, String action, String cause, String favorite) {
        super();

        this.code = code;
        this.erro = erro;
        this.action = action;
        this.cause = cause;
        this.favorite = favorite;
    }
*/
    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getErro() {
        return erro;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getCause() {
        return cause;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getFavorite() {
        return favorite;
    }
}
