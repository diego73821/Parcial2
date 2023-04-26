import java.util.Scanner;

class NodoCadena {
    private String dato;
    private NodoCadena izquierda, derecha;

    public NodoCadena(String dato) {
        this.dato = dato;
        this.izquierda = this.derecha = null;
    }


    public String getDato() {
        return dato;
    }
    
    public void setDato(String dato) {
        this.dato = dato;
    }

    public NodoCadena getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoCadena izquierda) {
        this.izquierda = izquierda;
    }

    public NodoCadena getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoCadena derecha) {
        this.derecha = derecha;
    }

    public void imprimirDato() {
        System.out.println(this.getDato());
    }
}


class ArbolCadenas {
    private NodoCadena raiz;

    public ArbolCadenas() {

    }

    public String existe(String busqueda) {
        return existe(this.raiz, busqueda);
    }

    private String existe(NodoCadena n, String busqueda) {
        if (n == null) {
            return "Este modelo NO existe, intentelo de nuevo";
        }
        if (n.getDato().equals(busqueda)) {
            return "Ha rentado exitosamente su moto, distrutela!";
        } else if (busqueda.compareTo(n.getDato()) < 0) {
            return existe(n.getIzquierda(), busqueda);
        } else {
            return existe(n.getDerecha(), busqueda);
        }

    }

    public void insertar(String dato) {
        if (this.raiz == null) {
            this.raiz = new NodoCadena(dato);
        } else {
            this.insertar(this.raiz, dato);
        }
    }

    private void insertar(NodoCadena padre, String dato) {
        if (dato.compareTo(padre.getDato()) > 0) {
            if (padre.getDerecha() == null) {
                padre.setDerecha(new NodoCadena(dato));
            } else {
                this.insertar(padre.getDerecha(), dato);
            }
        } else {
            if (padre.getIzquierda() == null) {
                padre.setIzquierda(new NodoCadena(dato));
            } else {
                this.insertar(padre.getIzquierda(), dato);
            }
        }
    }

    private void preorden(NodoCadena n) {
        if (n != null) {
            n.imprimirDato();
            preorden(n.getIzquierda());
            preorden(n.getDerecha());
        }
    }

    private void inorden(NodoCadena n) {
        if (n != null) {
            inorden(n.getIzquierda());
            n.imprimirDato();
            inorden(n.getDerecha());
        }
    }

    private void postorden(NodoCadena n) {
        if (n != null) {
            postorden(n.getIzquierda());
            postorden(n.getDerecha());
            n.imprimirDato();
        }
    }

    public void preorden() {
        this.preorden(this.raiz);
    }

    public void inorden() {
        this.inorden(this.raiz);
    }

    public void postorden() {
        this.postorden(this.raiz);
    }
    public void eliminar(String busqueda) {
        this.raiz = this.eliminar(this.raiz, busqueda);
    }

    private NodoCadena eliminar(NodoCadena NodoCadena, String busqueda) {
        if (NodoCadena == null) {
            return NodoCadena;
        }
        if (NodoCadena.getDato().equals(busqueda)) {
            NodoCadena.setDerecha(this.eliminar(NodoCadena.getDerecha(), busqueda));
        } else if (busqueda.compareTo(NodoCadena.getDato()) < 0) {
            NodoCadena.setIzquierda(this.eliminar(NodoCadena.getIzquierda(), busqueda));
        } else {
            if (NodoCadena.getIzquierda() == null && NodoCadena.getDerecha() == null) {
                NodoCadena = null;
            } else if (NodoCadena.getDerecha() != null) {
                NodoCadena.setDato(this.sucesor(NodoCadena));
                NodoCadena.setDerecha(this.eliminar(NodoCadena.getDerecha(), NodoCadena.getDato()));
            } else {
                NodoCadena.setDato(this.predecesor(NodoCadena));
                NodoCadena.setIzquierda(this.eliminar(NodoCadena.getIzquierda(), NodoCadena.getDato()));
            }
        }
        return NodoCadena;
    }

    private String sucesor(NodoCadena NodoCadena) {
        NodoCadena = NodoCadena.getDerecha();
        while (NodoCadena.getIzquierda() != null) {
            NodoCadena = NodoCadena.getIzquierda();
        }
        return NodoCadena.getDato();
    }

    private String predecesor(NodoCadena NodoCadena) {
        NodoCadena = NodoCadena.getIzquierda();
        while (NodoCadena.getDerecha() != null) {
            NodoCadena = NodoCadena.getDerecha();
        }
        return NodoCadena.getDato();
    }
}


public class Main {
    public static void main(String[] argumentos) {
        ArbolCadenas arbolCadenas = new ArbolCadenas();
        arbolCadenas.insertar("Susuki Gixxer 150");
        arbolCadenas.insertar("Auteco Pulsar NS");
        arbolCadenas.insertar("AKT NKD 125");
        arbolCadenas.insertar("Yamaha FZ25");
        arbolCadenas.insertar("KTM Duke200NG");
        System.out.println("Estos son las motos disponibles para renta (Insertar):");
        arbolCadenas.inorden();
        System.out.println("Seleccione un modelo de la lista (busqueda):");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println(arbolCadenas.existe(name)); // true
    }
}