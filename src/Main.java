import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

  public static void main(String[] args) {
    ArrayList<String> listaProductos = new ArrayList<>();
    listaProductos.add("Bloque EPS");
    listaProductos.add("Ladrillo cerámico");
    listaProductos.add("Ladrillo de hormigón");
    listaProductos.add("Ladrillo macizo");
    listaProductos.add("Ladrillo retak");
    listaProductos.add("Perfil PGC");
    listaProductos.add("Perfil PGU");

    Scanner entrada = new Scanner(System.in);

    int opcionUsuario;
    System.out.println("Bienvenido a materiales Hanoteriales");
    label:
    while(true) {
      System.out.println("""
          ¿Qué querés hacer?:
          1 - Ver productos disponibles
          2 - Buscar un producto
          3 - Crear un producto
          4 - Editar un producto
          5 - Borrar un producto
          0 - Finalizar
          """);
      opcionUsuario = entrada.nextInt();
      entrada.nextLine();
      switch (opcionUsuario) {
        case 1:
          mostrarProductos(listaProductos);
          pausa(entrada);
          break;
        case 2:
          buscarProducto(listaProductos, entrada);
          pausa(entrada);
          break;
        case 3:
          agregarProducto(listaProductos, entrada);
          pausa(entrada);
          break;
        case 4:
          editarProducto(listaProductos, entrada);
          pausa(entrada);
          break;
        case 5:
          borrarProducto(listaProductos, entrada);
          pausa(entrada);
          break;
        case 0:
          System.out.println("Gracias por elegirnos!");
          break label;
        default:
          System.out.println("Opción invalida...");
          break;
      }
    }
  }
  public static String formatearNombre(String nombreFormatear){
    String nombreFormateado;
    nombreFormateado = nombreFormatear.toLowerCase().trim();
    return ignorarTildes(nombreFormateado);
  }
  public static String ignorarTildes(String texto) {
    String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
    return textoNormalizado.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
  }
  public static void pausa(Scanner entrada) {
    System.out.println("""
        
        Pulse ENTER para continuar...
        
        """);
    entrada.nextLine();
  }
  public static void mostrarProductos(ArrayList <String> listaProductos){
    if (listaProductos.isEmpty()) {
      System.out.println("En este momento no tenemos productos para mostrarte");
    } else {
      int contador = -1;
      for (String producto : listaProductos) {
        contador++;
        System.out.println(contador + " " + producto);
      }
    }
    System.out.println(" ");
    //TODO: cambiar nombre de la entrada de lista, tiene el mismo que la lista original
  }
  public static void buscarProducto(ArrayList <String> listaProductos, Scanner entrada){
    //TODO: si pongo dos plabras que estan en dos productos distintos da mal
    System.out.println("¿Qué estás buscando?: ");
    ArrayList<String> coincidenciasProductos = new ArrayList<>();

    String busqueda = entrada.nextLine();

    for (String producto : listaProductos) {
      if (formatearNombre(producto).contains(formatearNombre(busqueda))) {
        coincidenciasProductos.add(producto);
      }
    }

    if (!coincidenciasProductos.isEmpty()) {
      System.out.println("Coincidencias: ");
    }
    mostrarProductos(coincidenciasProductos);
  }
  public static void agregarProducto(ArrayList <String> listaProductos, Scanner entrada){
    System.out.println("Creación de producto");
    System.out.print("¿Qué producto deseás agregar?: ");
    String nombre = entrada.nextLine();
    listaProductos.add(nombre);
    System.out.println("Producto agregado con éxito");
    //TODO: denegar la creacion si ya existe el producto
  }
  public static void editarProducto(ArrayList <String> listaProductos, Scanner entrada){
    mostrarProductos(listaProductos);
    //TODO: formatear el nombre que se le ponga
    //TODO: agragar, validacion, si pone un numero que no existe imprimir un msj
    int productoEditar;
    String nuevoProducto;
    System.out.print("Ingrese el número del producto que desea editar: ");
    productoEditar = entrada.nextInt();
    entrada.nextLine();
    System.out.print("Ingrese el nuevo nombre: ");
    nuevoProducto = entrada.nextLine();
    listaProductos.set(productoEditar, nuevoProducto);

    System.out.println("Producto editado con éxito");
  }
  public static void borrarProducto(ArrayList <String> listaProductos, Scanner entrada){
    mostrarProductos(listaProductos);
    System.out.print("Ingrese el número del producto que desea borrar: ");
    int productoBorrar;
    productoBorrar = entrada.nextInt();
    entrada.nextLine();
    listaProductos.remove(productoBorrar);
    System.out.println("Producto borrado con éxito");
  }
}
