package webdriver;

public class Topic_04_And_Or {
		public static void main (String[] arg){
			boolean statusA;
			boolean statusB;
			
			//AND
			//Nếu 1 trong 2 điều kiện sai -> Sai
			//Nếu 1 trong 2 điều điện đúng -> Sai
			//Cả 2 điều kiện sai -> sai
			//Cả 2 điều kiện đúng -> Đúng
			
			statusA = false;
			statusB = true;
			
			System.out.println("Kết quả = "+(statusA && statusB));
			
			//OR
			//Nếu 1 trong 2 điều kiện sai -> đúng
			//Nếu 1 trong 2 điều điện đúng -> đúng
			//Cả 2 điều kiện sai -> sai
			//Cả 2 điều kiện đúng -> Đúng
			
			statusA = false;
			statusB = true;
			
			System.out.println("Kết quả = "+(statusA && statusB));
			
			
		}

}
