import java.util.*;
import com.sun.jna.Library;
import com.sun.jna.Native;

public class LeticiaOlivoDariva_TCA {
    //usuário ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public final static Scanner ESCRITA_USUARIO = new Scanner(System.in);
    public static Cordenada lugarJogador1 = new Cordenada();
    public static Cordenada lugarJogador2 = new Cordenada();
    public static  int pega=escolhePegador();
    private static int ultimaAcaoJogador1 = 0;
    private static int ultimaAcaoJogador2 = 0;


    //tela ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public static char[][] tela = new char[50][160];

    //neutralizante
    public final static Random ALEATORIZA_EFEITOS = new Random();

    //tempo ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private static final long TEMPO_MS = 300; // O tempo mínimo que deve passar (100ms)
    private static long ultimoTempo = System.currentTimeMillis();


    //usuario ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public interface Crt extends Library {
        Crt INSTANCE = Native.load("msvcrt", Crt.class);

        int _kbhit();// Se uma tecla foi pressionada, ela retorna um valor diferente de zero.
        // Se _kbhit()!=0 chamamos _getch() para ler a tecla.
 
        int _getch();   // lê a tecla diretamente, sem esperar pelo ENTER, retorna o código ASCII da tecla pressionada.
    }

    public static boolean pressionouTecla() {
        return Crt.INSTANCE._kbhit() != 0;
    }

    public static int obtemTeclaPressionada() {
        return Crt.INSTANCE._getch();
    } // retorna o código ASCII da tecla pressionada

    public static void defineUsuario() {
        tela[lugarJogador1.absissaAtual][lugarJogador1.ordenadaAtual] = '&';
        
        tela[lugarJogador2.absissaAtual][lugarJogador2.ordenadaAtual] = '@';
    }//Define a posição inicial do usuario na matriz de char
    
    
    public static void movimentacaoJogador1(int asciiTecla) {
        switch (asciiTecla) {
            case '4':
                if (tela[lugarJogador1.absissaAtual][lugarJogador1.ordenadaAtual - 1] == 'y') {//simulo que ele foi para a esquerda
                    lugarJogador1.quantNeutralizantes++;
                }// se o usuario estiver na posição que tem um y(neutralizante) aumenta a quantidade de neutralizantes que ele tem
                lugarJogador1.ordenadaAtual--;
                //move o jogador & para a esquerda
                break;
            case '6':
                if (tela[lugarJogador1.absissaAtual][lugarJogador1.ordenadaAtual + 1] == 'y') {//simulo que ele foi para a direita
                    lugarJogador1.quantNeutralizantes++;
                }
                lugarJogador1.ordenadaAtual++;
                //move o jogador & para a direita
                break;
            case '8':
                if (tela[lugarJogador1.absissaAtual - 1][lugarJogador1.ordenadaAtual] == 'y') {
                    lugarJogador1.quantNeutralizantes++;
                }
                lugarJogador1.absissaAtual--;
                //move o jogador & para cima
                break;
            case '5':
                if (tela[lugarJogador1.absissaAtual + 1][lugarJogador1.ordenadaAtual] == 'y') {
                    lugarJogador1.quantNeutralizantes++;
                }
                lugarJogador1.absissaAtual++;
                //move o jogador & para a esquerda
                break;
            case '0':
                if (lugarJogador1.quantNeutralizantes>0) {//testa se o usuario tem neutralizantes para usar
                    lugarJogador1.quantNeutralizantes--;
                    lugarJogador2.tempoParado=5;
                }
                //utiliza o neutralizante
                break;
        }
    }
                
    public static void movimentacaoJogador2(int asciiTecla) {
        //mesmo funcionamento da função movimentoJogador1 só que muda o jogador e as teclas a serem clicadas
        switch (asciiTecla) {
            case 'a':
                if (tela[lugarJogador2.absissaAtual][lugarJogador2.ordenadaAtual - 1] == 'y') {
                    lugarJogador2.quantNeutralizantes++;
                }
                lugarJogador2.ordenadaAtual--;

                break;
            case 'A':
                if (tela[lugarJogador2.absissaAtual][lugarJogador2.ordenadaAtual - 1] == 'y') {
                    lugarJogador2.quantNeutralizantes++;
                }
                lugarJogador2.ordenadaAtual--;

                break;
            case 'd':
                if (tela[lugarJogador2.absissaAtual][lugarJogador2.ordenadaAtual + 1] == 'y') {
                    lugarJogador2.quantNeutralizantes++;
                }
                lugarJogador2.ordenadaAtual++;

                break;
            case 'D':
                    if (tela[lugarJogador2.absissaAtual][lugarJogador2.ordenadaAtual + 1] == 'y') {
                        lugarJogador2.quantNeutralizantes++;
                    }
                    lugarJogador2.ordenadaAtual++;
                    
                    break;
            case 'w':
                if (tela[lugarJogador2.absissaAtual - 1][lugarJogador2.ordenadaAtual] == 'y') {
                    lugarJogador2.quantNeutralizantes++;
                }
                lugarJogador2.absissaAtual--;

                break;
            case 'W':
                if (tela[lugarJogador2.absissaAtual - 1][lugarJogador2.ordenadaAtual] == 'y') {
                    lugarJogador2.quantNeutralizantes++;
                }
                lugarJogador2.absissaAtual--;

                break;
            case 's':
                if (tela[lugarJogador2.absissaAtual + 1][lugarJogador2.ordenadaAtual] == 'y') {
                    lugarJogador2.quantNeutralizantes++;
                }
                lugarJogador2.absissaAtual++;

                break;
            case 'S':
                if (tela[lugarJogador2.absissaAtual + 1][lugarJogador2.ordenadaAtual] == 'y') {
                    lugarJogador2.quantNeutralizantes++;
                }
                lugarJogador2.absissaAtual++;

                break;
            case 'f':
                if (lugarJogador2.quantNeutralizantes>0) {
                    lugarJogador2.quantNeutralizantes--;
                    lugarJogador1.tempoParado=5;
                }

                break;

            case 'F':
                if (lugarJogador2.quantNeutralizantes>0) {
                    lugarJogador2.quantNeutralizantes--;
                    lugarJogador1.tempoParado=5;
                }

                break;
        }
    }
    //tela ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                
    public static void defineBorda() {
        for (int linha = 0; linha < tela.length; linha++) {
            for (int coluna = 0; coluna < tela[0].length; coluna++) {
                if (coluna == 0 || coluna == (tela[0].length - 1) || linha == 0 || linha == (tela.length - 1)) {
                    tela[linha][coluna] = '§';
                }
            }
        }
    }//define o local que vai estar a borda do mapa
    
    public static void imprimeCena(int usuarioPegador) {
        for (int linha = 0; linha < tela.length; linha++) {
            System.out.printf("\n\t\t\t\t");
            for (int coluna = 0; coluna < tela[0].length; coluna++) {
                char charCena = tela[linha][coluna];
                if (charCena == 0 || charCena == '`') {//analisa se tem algo na matriz, se não tiver é o chão
                    defineCor(96);
                    System.out.print('`');
                } else {
                    if (charCena == '§') {//imprime borda
                        defineCor(84);
                        System.out.print(charCena);
                    } else if (charCena == '@') {//imprime usuario 2
                        if(usuarioPegador==1){
                            defineCor(101);
                        }else{
                            defineCor(1);
                        }
                        System.out.print(charCena);
                    } else if (charCena == '&') {//imprime usuario 1
                        if(usuarioPegador==0){
                            defineCor(101);
                            System.out.print(charCena);
                        }else{
                            defineCor(51);
                            System.out.print(charCena);
                        }
                    } else {
                        defineCor(178);
                        System.out.print(charCena);
                    }
                }
            }
        }
        System.out.printf("\n\n\t\t\t\t\t%d\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%d\t\t\n",
        lugarJogador2.quantNeutralizantes, lugarJogador1.quantNeutralizantes);//imprime quantidade de neutralizantes de cada um
    }
    
    public static void arrumaTela() {
        if(lugarJogador1.absissaAtual+2>=3&&lugarJogador1.ordenadaAtual+33<=158+33&&lugarJogador2.absissaAtual+2<=50&&lugarJogador2.ordenadaAtual+33>=34){
            gotoXY(lugarJogador1.absissaAntiga+2, lugarJogador1.ordenadaAntiga+33);
            defineCor(96);
            System.out.print('`');
            gotoXY(lugarJogador1.absissaAtual+2, lugarJogador1.ordenadaAtual+33);
            if(pega==0){
                defineCor(101);
            }else{
                defineCor(51);
            }
            System.out.print('&');
            gotoXY(lugarJogador2.absissaAntiga+2, lugarJogador2.ordenadaAntiga+33);
            defineCor(96);
            System.out.print('`');
            gotoXY(lugarJogador2.absissaAtual+2, lugarJogador2.ordenadaAtual+33);
            if(pega==1){
                defineCor(101);
            }else{
                defineCor(9);
            }
            System.out.print('@');
            gotoXY(53, 41);
            defineCor(25);
            System.out.print(lugarJogador2.quantNeutralizantes);
            gotoXY(53, 185);
            defineCor(25);
            System.out.print(lugarJogador1.quantNeutralizantes);
        }
        atualizaAntiga();
    }

    public static void reiniciaCor() {
        System.out.print("\u001b[0m");
    }

    public static void defineCor(int cor) {
        if (cor < 0 || cor > 255) {
            reiniciaCor();
            return;
        }
        System.out.print("\u001b[38;5;" + cor + "m");
    }

    public static void limparConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // efeitos ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public static Efeito[] definePosicaoEfeitos(int quantValores) {

        Efeito[] efeitos = new Efeito[quantValores];
        int index = quantValores - 1;

        while (index >= 0) {
            int linhaInt = ALEATORIZA_EFEITOS.nextInt(tela.length - 2) + 1;
            int colunaInt = ALEATORIZA_EFEITOS.nextInt(tela[0].length - 2) + 1;

            if (tela[linhaInt][colunaInt] == 0) {
                Efeito efeito = new Efeito();
                efeito.x = linhaInt;
                efeito.y = colunaInt;
                efeito.letra = 'y';
                efeitos[index] = efeito;
                index--;
            }
        }
        return efeitos;
    }

    public static int defineQuantidadeNeutralizantes() {
        int valorInt = ALEATORIZA_EFEITOS.nextInt(100);
        int valorCena = 0;
        if (valorInt < 33) {
            valorCena = 5;
        } else if (valorInt < 66) {
            valorCena = 15;
        } else if (valorInt < 99) {
            valorCena = 45;
        } else {
            valorCena = 80;
        }

        return valorCena;
    }

    public static void configuraNeutralizante(Efeito[] efeitos) {

        for (int i = 0; i < efeitos.length; i++) {
            int x = efeitos[i].x;
            int y = efeitos[i].y;
            tela[x][y] = efeitos[i].letra;

        }
    }

    //outros ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void iniciaVariaveis() {
        lugarJogador1.absissaAtual = 1;
        lugarJogador1.ordenadaAtual = 158;
        lugarJogador1.absissaAntiga = 1;
        lugarJogador1.ordenadaAntiga = 158;
        lugarJogador1.quantNeutralizantes = 0;
        lugarJogador2.absissaAtual = 48;
        lugarJogador2.ordenadaAtual = 1;
        lugarJogador2.absissaAntiga = 48;
        lugarJogador2.ordenadaAntiga = 1;
        lugarJogador2.quantNeutralizantes = 0;
    }

    public static void atualizaAntiga() {
        lugarJogador2.absissaAntiga = lugarJogador2.absissaAtual;
        lugarJogador2.ordenadaAntiga = lugarJogador2.ordenadaAtual;

        lugarJogador1.absissaAntiga = lugarJogador1.absissaAtual;
        lugarJogador1.ordenadaAntiga = lugarJogador1.ordenadaAtual;
    }

    public static void gotoXY(int linha, int coluna) {
        char escCode = 0x1B;
        System.out.print(String.format("%c[%d;%df", escCode, linha, coluna));
    }

    public static int atualizaAcaoJogadores() {

        if (pressionouTecla()) {
            int valorAscii = obtemTeclaPressionada();

            if (valorAscii == '4' || valorAscii == '5' || valorAscii == '8' || valorAscii == '6'|| valorAscii =='0') {
                ultimaAcaoJogador1 = valorAscii;
            }

            if (valorAscii == 'a' || valorAscii == 's' || valorAscii == 'd' || valorAscii == 'w'|| valorAscii=='f'|| valorAscii == 'A' || valorAscii == 'S' || valorAscii == 'D' || valorAscii == 'W'|| valorAscii=='F') {
                ultimaAcaoJogador2 = valorAscii;
            }

            if (valorAscii == 27) {
                return -1;
            }

        }
        return 0;

    }

    public static int escolhePegador() {
        Random selecionaPegador=new Random();
        int pegador=selecionaPegador.nextInt(2);
        return pegador;
    }

    
    public static int mudaPegador() {
        if(lugarJogador1.absissaAtual==lugarJogador2.absissaAtual&&lugarJogador1.ordenadaAtual==lugarJogador2.ordenadaAtual){
            if(pega==0){
                pega=1;
            }else{
                pega=0;
            }
        }
        return pega;
    }

    public static void executaAcoes() {

        if (ultimaAcaoJogador1 != 0 || ultimaAcaoJogador2 != 0) {
            if(lugarJogador1.tempoParado==0){
                movimentacaoJogador1(ultimaAcaoJogador1);
                if(lugarJogador2.tempoParado>0){
                    lugarJogador2.tempoParado--;
                }
            }
            if(lugarJogador2.tempoParado==0){
                movimentacaoJogador2(ultimaAcaoJogador2);
                if(lugarJogador1.tempoParado>0){
                   lugarJogador1.tempoParado--;
                }
            }
            //System.out.printf("jogador1  %d --   jogador2  %d\n", ultAcaoJog1, ultAcaoJog2);

            arrumaTela();
        }
        ultimaAcaoJogador1 = 0;
        ultimaAcaoJogador2 = 0;
    }

    public static void main(String[] args) {

        int numEfeitos = defineQuantidadeNeutralizantes();
        Efeito[] efeitos = definePosicaoEfeitos(numEfeitos);
        iniciaVariaveis();
        limparConsole();
        defineBorda();
        defineUsuario();
        configuraNeutralizante(efeitos);
        imprimeCena(pega);
        long tempo =System.currentTimeMillis();
        atualizaAntiga();
        Random defineTempo=new Random();
        long tempoMinimo=60000;
        long tempoUtilizado=(defineTempo.nextLong(30000))+tempoMinimo;


        while (true) {
            int analisaEsc = atualizaAcaoJogadores();
            if (analisaEsc == -1) {
                break;
            }

            long tempoAtual = System.currentTimeMillis();
            if(tempoAtual-tempo>=tempoUtilizado){
                break;
            }

            long tempoDecorrido = tempoAtual - ultimoTempo;

            if (tempoDecorrido >= TEMPO_MS) {

                executaAcoes();
                mudaPegador();
                ultimoTempo = tempoAtual;
            }
        }
        if(pega==0){
            System.out.printf("\n\tResultados finais\n  @: %s\t&: %s","vencedor","perdedor");
        }else{
            System.out.printf("\n\tResultados finais\n  @: %s\t&: %s","perdedor","vencedor");
        }
    }
}
