package TaskB;

import java.util.Arrays;
import java.util.Random;

public class TaskB {
	public static void execute(String string)
	{
		int numberOfFragments = string.length() / 3;
		if (string.length() % 3 != 0)
		{
			numberOfFragments++;
		}
		String[] fragments = new String[numberOfFragments];
		for (int i = 0; i < numberOfFragments; i++)
		{
			fragments[i] = string.substring(i * 3, i * 3 + 3);
			System.out.println(fragments[i]);
		}
		Random random = new Random();
		for (int i = 0; i < numberOfFragments; i++)
		{
			char first = fragments[i].charAt(0);
			char second = fragments[i].charAt(1);
			char third = fragments[i].charAt(2);
			char symbol = (char)(random.nextInt(26) + 'a');
			
			while(symbol == first || symbol == second || symbol == third)
			{
				symbol = (char)(random.nextInt(26) + 'a');
			}
			fragments[i] = new String(new char[] {first, symbol, third});
			System.out.println(fragments[i]);
		}
		
		Arrays.sort(fragments);
		for (int i = 0; i < numberOfFragments; i++)
		{
			System.out.println(fragments[i]);
		}
	}
}
