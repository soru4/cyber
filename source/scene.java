class scene{
    public scene(){
        //add the classes that extend MonoBehaviour here( just call the constructor)
        gameloop.__inst__.Instantiate( new TextCounter(),0);
    }
}