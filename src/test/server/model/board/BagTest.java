package server.model.board;


import junit.framework.TestCase;


public class BagTest
        extends TestCase
{

    public void testExtract1()
    {
        Bag b = new Bag();
        assertNotNull( b.extract() );
    }

    public void testNoMoreTiles1(){
        Bag b = new Bag();
        assertFalse(b.NoMoreTiles());
    }

    public void testExtract2(){
        Bag b=new Bag();
        for(int i=0;i<67;i++){
            b.extract();
        }
        assertNotNull(b.extract());
    }

    public void testExtract3(){
        Bag b=new Bag();
        for(int i=0;i<=132;i++){
            b.extract();
        }
        assertNull(b.extract());
    }

    public void testNoMoreTiles2(){
        Bag b=new Bag();
        for(int i=0;i<=132;i++){
            b.extract();
        }
        assertTrue(b.NoMoreTiles());
    }
}

