public class test {
    public static void main(String[] args)  {

        try {
            System.out.println(promo(894.23f));
        }catch(PriceException s){
            System.out.println(s.getMessage());
        }

    }

    public static float promo(float price) throws PriceException,ArithmeticException{
        if(price == 0){
            throw new PriceException();
        }else {
            float finalPrice = (price * 24) / 100;

            return price - finalPrice;
        }
    }
}
