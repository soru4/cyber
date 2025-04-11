public class MonoBehaviour{

    public gameloop gameRunner; 

    public MonoBehaviour(gameloop x){
        this.gameRunner = x;
        gameRunner.Instantiate(this,1f);
    }

    public void Start(){

    }


    public void Update(){

    }
}