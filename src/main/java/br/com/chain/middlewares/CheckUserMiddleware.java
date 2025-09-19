package br.com.chain.middlewares;

import br.com.chain.server.UserService;

public class CheckUserMiddleware  extends Middleware{
    private UserService server;

    public CheckUserMiddleware(UserService server){
        this.server = server;
    }

    @Override
    public boolean check(String email, String password) {
        if(!server.hasEmail(email)){
            System.out.println("E-mail Inválido");
            return false;
        }

        if(!server.isValidPassword(email, password)){
            System.out.println("E-mail ou Senha Inválidos");
            return false;
        }
        return checkNext(email, password);
    }
}
