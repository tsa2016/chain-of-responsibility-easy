package br.com.chain;

import br.com.chain.middlewares.CheckPermissionMiddleware;
import br.com.chain.middlewares.CheckUserMiddleware;
import br.com.chain.middlewares.Middleware;
import br.com.chain.server.UserService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static UserService userService;

    public static void init(){
        userService = new UserService();
        userService.registerUser("master@hcode.com.br", "8765rtyuTRE##%");
        userService.registerUser("user@hcode.com.br", "123456");

        Middleware middleware = new CheckUserMiddleware(userService);
        middleware.linkWith(new CheckPermissionMiddleware());

        userService.setMiddleware(middleware);
    }
    public static void main(String[] args) throws IOException {
        init();

        boolean done;

        do{
            System.out.println("Digite o e-mail: ");
            String email = reader.readLine();
            System.out.println("Digite sua senha: ");
            String password = reader.readLine();
            done = userService.logIn(email, password);
        }while(!done);

    }
}
