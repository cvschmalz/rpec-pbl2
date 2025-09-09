package listaencadeada;
import java.util.Scanner;


public class ListaEncadeada {
    private Node Lista;
    public ListaEncadeada(){
        this.Lista = null;
    }

    //Inserindo elementos
    public void inserir (int informacao){
        //Declarando nosso novo nó
        Node no = new Node();
        
        no.setInformacao(informacao);
        if(Lista == null){
            Lista = no;
        }
        else{
           //Aqui se cria um apontador para a lista.
            Node atual = Lista;
            while(atual.getProximo()!=null){
                atual = atual.getProximo();
            }
            atual.setProximo(no);
//            System.out.println("Nó atual no endereço" + atual);
        }
        
    }
    

    // PBL2

    // a) verifica se está vazia
    public boolean vazia(){
        return Lista == null;
    }

    // b) insere o elemento info como primeiro na Lista
    public void inserePrimeiro(int info){
        Node newNode = new Node();
        newNode.setInformacao(info);
        newNode.setProximo(Lista);
        Lista = newNode;
    }

    // c) insere o elemento info depois do nó node
    public void insereDepois(Node node, int info){
        if (node == null) {
            System.out.println("Nó nulo");
            return;
        }
        Node newNode = new Node();
        newNode.setInformacao(info);
        newNode.setProximo(node.getProximo());
        node.setProximo(newNode);
    }

    // d) insere o elemento info como último na Lista
    public void insereUltimo(int info){
        Node newNode = new Node();
        newNode.setInformacao(info);

        if (Lista == null) {
            Lista = newNode;
            return;
        }

        Node atual = Lista;
        while (atual.getProximo() != null){
            atual = atual.getProximo();
        }
        atual.setProximo(newNode);
    }

    // e) insere o elemento info de maneira ordenada na Lista
    public void insereOrdenado(int info){
        Node node = new Node();
        node.setInformacao(info);

        if (Lista == null || info < Lista.getInformacao()){
            node.setProximo(Lista);
            Lista = node;
            return;
        }

        Node atual = Lista;
        while (atual.getProximo() != null && atual.getProximo().getInformacao() < info) {
            atual = atual.getProximo();
        }
        node.setProximo(atual.getProximo());
        atual.setProximo(node);

    }

    // f) imprime todos os elementos da Lista
    public void imprime(){
        if (vazia()) {
            System.out.println("Lista vazia");
            return;
        }
        Node atual = Lista;
        while (atual != null){
            System.out.println(atual.getInformacao());
            atual = atual.getProximo();
        }
    }

    // g) remove o primeiro elemento da Lista, e retorna o node removido
    public Node removePrimeiro(){
        if (vazia()) {
            System.out.println("Lista vazia");
            return null;
        }
        Node removido = Lista;
        Lista = Lista.getProximo();
        removido.setProximo(null);
        return removido;
    }

    // h) remove o último elemento da Lista, e retorna o node removido.
    public Node removeUltimo(){
        if (vazia()) {
            System.out.println("Lista vazia");
            return null;
        }
        if (Lista.getProximo() == null) {
            Node removido = Lista;
            Lista = null;
            return removido;
        }
        Node atual = Lista;
        while (atual.getProximo().getProximo() != null){
            atual = atual.getProximo();
        }
        Node removido = atual.getProximo();
        atual.setProximo(null);
        return removido;
    }

    // i) remove (Node no): remove o node da Lista, e retorna o node removido, você deve escolher qual no será removido, por índice.
    // O item pede que o parâmetro seja um Node, mas o enunciado pede um índice, então fiz dois métodos.

    // Remove por Node:
    public Node remove(Node no){
        if (vazia()) {
            System.out.println("Lista vazia");
            return null;
        }
        if (no == null){
            System.out.println("Nó nulo");
            return null;
        }

        if (no == Lista) {
            return removePrimeiro();
        }

        Node atual = Lista;
        while (atual != null && atual.getProximo() != no) {
            atual = atual.getProximo();
        }
        if (atual == null) {
            System.out.println("Nó nulo");
            return null;
        }
        atual.setProximo(no.getProximo());
        no.setProximo(null);
        return no;

    }

    // Remove por índice:
    public Node removePorIndice(int indice){
        if (vazia()){
            System.out.println("Lista vazia");
            return null;
        }

        if (indice == 0){
            return removePrimeiro();
        }

        Node anterior = Lista;
        Node atual = Lista.getProximo();
        int i = 1;

        while (atual != null && i < indice){
            anterior = atual;
            atual = atual.getProximo();
            i++;
        }

        if (atual == null){
            System.out.println("Indice invalido");
            return null;
        }

        anterior.setProximo(atual.getProximo());
        atual.setProximo(null);
        return atual;
    }


    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaEncadeada lista = new ListaEncadeada();
        
        
        System.out.println("Digite os itens da lista (digite '0' para parar):");
        int item;
        while (true) {
            item = scanner.nextInt();
            if (item == 0) {
                break;
            }
            lista.inserir(item);
        }
        System.out.println("Lista Encadeada:");
        lista.imprime();

        int opcao, valor, anterior;
        Node atual;

        do {
            System.out.println("1. vazia()\n2. inserePrimeiro(int info)\n3. insereDepois(Node node, int info)\n4. insereUltimo(int info)\n5. insereOrdenado(int info)\n6. imprime()\n7. removePrimeiro()\n8. removeUltimo()\n9. remove(Node no)\n10. removePorIndice(int indice)");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    if (lista.vazia()){
                        System.out.println("Lista está vazia");
                    } else {
                        System.out.println("Lista não está vazia");
                    }

                    break;

                case 2:
                    System.out.println("Escolha número para inserir: ");
                    valor = scanner.nextInt();
                    lista.inserePrimeiro(valor);
                    lista.imprime();
                    break;

                case 3:
                    System.out.println("Escolha número para inserir: ");
                    valor = scanner.nextInt();

                    System.out.println("Escolha nó: ");
                    anterior = scanner.nextInt();

                    atual = lista.Lista;
                    while (atual.getInformacao() != anterior && atual.getProximo() != null){
                        atual = atual.getProximo();
                    }

                    lista.insereDepois(atual, valor);

                    lista.imprime();

                    break;

                case 4:
                    System.out.println("Escolha número para inserir: ");
                    valor = scanner.nextInt();

                    lista.insereUltimo(valor);
                    lista.imprime();

                    break;

                case 5:
                    System.out.println("Escolha número para inserir: ");
                    valor = scanner.nextInt();

                    lista.insereOrdenado(valor);
                    lista.imprime();

                    break;

                case 6:
                    lista.imprime();
                    break;

                case 7:
                    System.out.println("Removido: "+ lista.removePrimeiro());
                    lista.imprime();
                    break;

                case 8:
                    System.out.println("Removido: "+ lista.removeUltimo());
                    lista.imprime();
                    break;

                case 9:
                    System.out.println("Escolha nó: ");
                    anterior = scanner.nextInt();

                    atual = lista.Lista;
                    while (atual.getInformacao() != anterior && atual.getProximo() != null){
                        atual = atual.getProximo();
                    }
                    if (atual.getInformacao() != anterior){
                        System.out.println("Nó não encontrado");
                    } else {
                        System.out.println("Removido: " + lista.remove(atual));
                        lista.imprime();
                    }
                    break;

                case 10:
                    System.out.println("Escolha índice: ");
                    valor = scanner.nextInt();

                    System.out.println("Removido: " + lista.removePorIndice(valor));
                    lista.imprime();
                    break;
            }

        } while (opcao != 0);

        scanner.close();
       
    }
    
}
