public class Test
{
	public static void main(String[] args)
	{
		String couleur = "151262";
		
		int r = Integer.valueOf(couleur.substring(0,2), 16);
		int g = Integer.valueOf(couleur.substring(2,4), 16);
		int b = Integer.valueOf(couleur.substring(4,6), 16);
		//System.out.println(r);
		//System.out.println(g);
		//System.out.println(b);


		System.out.println((int) (Math.random() * 255)); 
	}
}