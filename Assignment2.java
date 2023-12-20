public class Assignment2
{
    static String xResult = "";
    static String yResult = "";
    static double resultWeight = 0.0;

    public static void sequence(String x, String y, double [][] matrix)
    {
        int n = x.length();
        int m = y.length();
        double [][] results = new double[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0) {
                    results[i][j] = 0.0;
                } else {
                    double weight1 = results[i - 1][j - 1] + matrix[findIndex(x.charAt(i - 1))][findIndex(y.charAt(j - 1))];
                    double weight2 = results[i - 1][j] + matrix[findIndex(x.charAt(i - 1))][findIndex('-')];
                    double weight3 = results[i][j - 1] + matrix[findIndex('-')][findIndex(y.charAt(j - 1))];
                    double temp = Math.max(weight1, Math.max(weight2, weight3));
                    results[i][j] = temp;
                    //System.out.println("The score in i = " + i + " and j = " + j + " is: " + results[i][j]);
                }
            }
        }

        while(n > 0 || m > 0)
        {
            //System.out.println("Aligned x till now: " + xResult);
            //System.out.println("Aligned y till now: " + yResult);
            if( n > 0 && m > 0 && results[n][m] == results[n-1][m-1] + matrix[findIndex(x.charAt(n-1))][findIndex(y.charAt(m-1))])
            {
                xResult = x.charAt(n-1) + xResult;
                yResult = y.charAt(m-1) + yResult;
                resultWeight += matrix[findIndex(x.charAt(n-1))][findIndex(y.charAt(m-1))];
                n--;
                m--;
            }
            else if (n > 0 && results[n][m] == results[n-1][m] + matrix[findIndex(x.charAt(n-1))][findIndex('-')])
            {
                xResult = x.charAt(n-1) + xResult;
                yResult = '-' + yResult;
                resultWeight += matrix[findIndex(x.charAt(n-1))][findIndex('-')];
                n--;
            }
            else 
            {
                xResult = '-' + xResult;
                yResult = y.charAt(m-1) + yResult;
                resultWeight += matrix[findIndex('-')][findIndex(y.charAt(m - 1))];
                m--;
            }
        }
    }

    public static int findIndex(char a)
    {
        if (a == 'A')
            return 0;
        else if(a == 'G')
            return 1;
        else if(a == 'T')
            return 2;
        else if(a == 'C')
            return 3;
        else
            return 4;
    }

    public static void main(String[]args)
    {
        double[][] weightMatrix = 
        {
            {1, -0.8, -0.2, -2.3, -0.6},
            {-0.8, 1, -1.1, -0.7, -1.5},
            {-0.2, -1.1, 1, -0.5, -0.9},
            {-2.3, -0.7, -0.5, 1, -1},
            {-0.6, -1.5, -0.9, -1, -500}
        };

        String x = "TCCCAGTTATGTCAGGGGACACGAGCATGCAGAGAC";
        String y = "AATTGCCGCCGTCGTTTTCAGCAGTTATGTCAGATC";
        // String x = "ATGCC";
        // String y = "TACGCA";
        sequence(x, y, weightMatrix);
        System.out.println("Aligned x: " + xResult);
        System.out.println("Aligned y: " + yResult);
        System.out.println("The resultant weight: " + resultWeight);

    }

}