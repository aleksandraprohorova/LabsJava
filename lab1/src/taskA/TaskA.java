package taskA;

public class TaskA {
	public static void execute()
	{
		final int rows = 20;
		final int columns = 10;
		int[][] matrix = new int[rows][columns];
		
		for (int i = 0; i < rows; i++)
		{
			matrix[i][0] = 1;
			System.out.print(matrix[i][0] + " ");
			for (int j = 1; j < columns; j++)
			{
				matrix[i][j] = 0;
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
