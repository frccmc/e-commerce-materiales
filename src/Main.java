import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

  public static void main(String[] args) {

    ArrayList<Producto> listaProductos = obtenerProductos();

    Scanner entrada = new Scanner(System.in);

    int opcionUsuario;
    System.out.println("Bienvenido a materiales Hano");
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
  public static ArrayList<Producto> obtenerProductos(){
    ArrayList<Producto> productos = new ArrayList<>();
    productos.add(new Producto("Bloque EPS"));
    productos.add(new Producto("Ladrillo cerámico"));
    productos.add(new Producto("Ladrillo de hormigón"));
    productos.add(new Producto("Ladrillo macizo"));
    productos.add(new Producto("Ladrillo retak"));
    productos.add(new Producto("Perfil PGC"));
    productos.add(new Producto("Perfil PGU"));
    return productos;
  }
  public static String formatearNombre(Producto nombreFormatear){

    String nombreFormateado;
    String nombreFormatearCadena = nombreFormatear.nombre;
    nombreFormateado = nombreFormatearCadena.toLowerCase().trim();
    return ignorarTildes(nombreFormateado);
  }
  public static String ignorarTildes(String texto) {
    String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
    return textoNormalizado.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    //NO LO TERMINO DE ENTENDER, LO HICE CON IA
  }
  public static void pausa(Scanner entrada) {
    System.out.println("""
        
        Pulse ENTER para continuar...
        
        """);
    entrada.nextLine();
  }
  public static void mostrarProductos(ArrayList <Producto> lista){

    //TODO: una f para acceder a cada producto

    if (lista.isEmpty()) {
      System.out.println("En este momento no tenemos productos para mostrarte");
    } else {
      int contador = -1;
      for (Producto material : lista) {
        contador++;
        String materialNombre = material.nombre;
        System.out.println(contador + " " + materialNombre);
      }
    }
    System.out.println(" ");
    //TODO: cambiar nombre de la entrada de lista, tiene el mismo que la lista original
  }
  public static void buscarProducto(ArrayList <Producto> listaProductos, Scanner entrada){
    //TODO: si pongo dos plabras que estan en dos productos distintos da mal
    System.out.println("¿Qué estás buscando?: ");
    ArrayList<Producto> coincidenciasProductos = new ArrayList<>();

    String busqueda = entrada.nextLine();

    for (Producto producto : listaProductos) {
      if (formatearNombre(producto).contains(formatearNombre(new Producto(busqueda)))) {
        coincidenciasProductos.add(producto);
      }
    }

    if (!coincidenciasProductos.isEmpty()) {
      System.out.println("Coincidencias: ");
    }
    mostrarProductos(coincidenciasProductos);
  }
  public static void agregarProducto(ArrayList <Producto> listaProductos, Scanner entrada){
    System.out.println("Creación de producto");
    System.out.print("¿Qué producto deseás agregar?: ");
    String nombre = entrada.nextLine();
    listaProductos.add(new Producto(nombre));
    System.out.println("Producto agregado con éxito");
    //TODO: denegar la creacion si ya existe el producto
  }
  public static void editarProducto(ArrayList <Producto> listaProductos, Scanner entrada){
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

    listaProductos.set(productoEditar, new Producto (nuevoProducto));

    System.out.println("Producto editado con éxito");
  }
  public static void borrarProducto(ArrayList <Producto> listaProductos, Scanner entrada){
    mostrarProductos(listaProductos);
    System.out.print("Ingrese el número del producto que desea borrar: ");
    int productoBorrar;
    productoBorrar = entrada.nextInt();
    entrada.nextLine();
    listaProductos.remove(productoBorrar);
    System.out.println("Producto borrado con éxito");
  }
}
