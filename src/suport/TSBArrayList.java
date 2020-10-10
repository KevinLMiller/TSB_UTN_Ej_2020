package suport;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class TSBArrayList<E> extends AbstractList<E>
        implements Iterable<E>, Serializable  {
    //como lo vi en clase cada item va a ser considerado un objet, por mas que ya sea generalizado esto..
    private Object[] items;
    //tamaño inicial de arreglo
    private int initial_capacity;
    //y la cantidad de casillas realmente usadas
    private int count;



    public TSBArrayList(){
        this(10);
    }

    @Override
    public int indexOf(Object o) {
        return super.indexOf(o);
    }

    public TSBArrayList(int initialCapacity){
        if (initialCapacity <= 0){
            initialCapacity = 10;
        }
        items = new Object[initialCapacity];
        initial_capacity =  initialCapacity;
        count = 0;
    }

    public E get(int index){
        if (index < 0 || index >= count)
        {
            throw new IndexOutOfBoundsException("indice fuera de rango.");
        }
        return (E) items[index];
    }

    public int size(){return count;}

    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder();
        texto.append("{");
        for (int i = 0; i < count ; i++) {
            texto.append(items[i]);
            if (i < count-1);
            {
                texto.append("...");
            }
        }
        texto.append("}");
        return texto.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new TSBArrayListIterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) {

    }

    public boolean add(E e){
        if(e == null) return false;
        if(count == items.length) this.ensureCapacity(items.length *2);

        items[count] = e;
        count ++;
        this.modCount++;
        return true;}


    public void add(int index, E e) {
        if(index > count || index <0)
        {
            throw new IndexOutOfBoundsException("fuera de rango.");
        }

        if(e == null) return;

        if(count == items.length) this.ensureCapacity(items.length * 2);

        int t = count;
        System.arraycopy(items,index,items, index+1,t);
        count ++;
        this.modCount ++;
    }


    public E remove(int index) {
        if(index >= count || index < 0){
            throw new IndexOutOfBoundsException("indice fuera de rango..");
        }
        int t = items.length;
        if(count < t/2){this.ensureCapacity(t/2);}

        Object old = items[index];
        int n = count;
        System.arraycopy(items,index+1,items,index,n-index-1);
        count --;
        items[count] = null;

        this.modCount --;
        return (E) old;
    }


    public void clear(){
        items = new Object[initial_capacity];
        count = 0;

        this.modCount = 0;
    }


    private void ensureCapacity(int minCapacity) {
        if(minCapacity == items.length) return;
        if(minCapacity < count) return;

        Object[] temp = new Object[minCapacity];
        System.arraycopy(items, 0,temp, 0,count);
        items = temp;

    }


    public boolean isEmpty() {return(count == 0); }


    public boolean contain(Object e)
    {
        if(e == null) return false;

        for (int i = 0; i < count; i++) {
            if(e.equals(items[i])) return true;
        }
        return false;
    }


    public E set(int index, E element)
    {
        if(index < 0 || index >= count)
        {
            throw new IndexOutOfBoundsException("set(): indice fuera de rango");
        }
        Object old = items[index];
        items[index] = element;
        return(E) old;
    }


    /**
     * Ajusta el tamaño del arreglo de soporte, para que coincida con el tamaño
     * de la lista. Puede usarse este método para que un programa ahorre un poco
     * de memoria en cuanto al uso de la lista, si es necesario.
     */
    public void trimToSize()
    {
        if(count == items.length) return;

        Object temp[] = new Object[count];
        System.arraycopy(items, 0, temp, 0, count);
        items = temp;
    }




    private class TSBArrayListIterator implements Iterator<E> {
        private int current = -1;
        private boolean next_ok = false;

        @Override
        public boolean hasNext() {
            if(isEmpty()) {return false; }
            if(current >= size()-1){return false;}
            return true;
        }

        @Override
        public E next() {
            if (!hasNext()){throw new NoSuchElementException("no quedan elementos para recorrer");}

            current ++;
            next_ok = true;
            return (E) items[current];

        }

        @Override
        public void remove() {
            if (!next_ok) {throw new IllegalStateException("Debe invocar a next() antes de remove()");}
            E garbage = TSBArrayList.this.remove(current);
            next_ok = false;
            current--;

        }
    }




    }

