public class Test {

    public static void main(String[] args) {
        try{
            new Test().test();
        }catch (Exception e){
            e.getMessage();
            System.out.println("123");
        }
    }

    private void thro() throws Exception {
        throw new Exception("ex");
    }

    public void test(){
        try {
            thro();
        } catch (Exception e) {
            e.getMessage();
        }
    }

}