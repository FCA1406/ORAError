package br.com.decade.oraerror.server;


public class ORAErrorBO {
    private String code;
    private String erro;
    private String cause;
    private String action;
    private String favorite;

    public ORAErrorBO() {
        super();
    }
/*
    public ORAErrorBO(String code, String erro, String cause, String action, String favorite) {
        super();

        this.code = code;
        this.erro = erro;
        this.cause = cause;
        this.action = action;
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

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getCause() {
        return cause;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getFavorite() {
        return favorite;
    }
}
