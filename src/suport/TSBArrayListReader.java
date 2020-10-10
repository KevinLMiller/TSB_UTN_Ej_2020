package suport;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class TSBArrayListReader {
    private String archivo = "lista.dat";

    public TSBArrayListReader(){}

    public TSBArrayListReader(String nombre){
        archivo = nombre;
    }

    public soporte.TSBArrayList read() throws soporte.TSBArrayListIOException
    {
        soporte.TSBArrayList al = null;
        try{
            FileInputStream istream = new FileInputStream(archivo);//"C:\\Users\\usuario\\Desktop\\TSBç\\ArchivosCreadosJava\\"
            ObjectInputStream p = new ObjectInputStream(istream);

            al = (soporte.TSBArrayList) p.readObject();

            p.close();
            istream.close();
        }
        catch (Exception e){
            throw new soporte.TSBArrayListIOException("No se pudo recuperar la lista");
        }
        return al;
    }
}
