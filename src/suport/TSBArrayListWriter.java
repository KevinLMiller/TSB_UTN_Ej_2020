package suport;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class TSBArrayListWriter {
    private String archivo = "lista.dat";

    public TSBArrayListWriter()
    {
    }

    public TSBArrayListWriter(String nombre){ archivo = nombre; } //"C:\\Users\\usuario\\Desktop\\TSBç\\ArchivosCreadosJava\\"

    public void write (TSBArrayList al) throws TSBArrayListIOException{
        try
        {
            FileOutputStream ostream = new FileOutputStream(archivo);
            ObjectOutputStream p = new ObjectOutputStream(ostream);

            p.writeObject(al);

            p.flush();
            ostream.close();
        }
        catch (Exception e)
        {
            throw new TSBArrayListIOException("No se pudo grabar la lista...");
        }
    }
}
