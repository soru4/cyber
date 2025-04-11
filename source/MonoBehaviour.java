public abstract class MonoBehaviour {

    public MonoBehaviour() {

        gameloop.__inst__.Instantiate(this, 0f);
    }

    public abstract void Start();

    public abstract void Update();
}