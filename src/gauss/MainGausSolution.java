package gauss;

public class MainGausSolution
{
    public void makeTriangle(LinearSystem<Double, MyEquation> list)
    {
        Algorithm<Double, MyEquation> alg = new Algorithm<>(list);
        try
        {
            alg.calculate();
        }
        catch (NullPointerException | ArithmeticException e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }

    }

    public Double[] findSolution(LinearSystem<Double, MyEquation> list, boolean alreadyTriangle)
    {
        Double[] x = new Double[list.size()];
        if (!alreadyTriangle)
        {
            makeTriangle(list);
            System.out.println("MakeTriangle: ");
            printSystem(list);
        }
        int i, j;
        for (i = list.size() - 1; i >= 0; i--)
        {
            double sum = 0.0;
            for (j = list.size() - 1; j > i; j--)
            {
                sum += list.itemAt(i, j) * x[j];
            }
            x[i] = (list.itemAt(i, list.size()) - sum) / list.itemAt(i, j);

        }
        return x;
    }

    public void printSystem(LinearSystem<Double, MyEquation> system)
    {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < system.size(); i++)
        {
            MyEquation temp = system.get(i);
            for (int j = 0; j < temp.size(); j++)
            {
                s.append(system.itemAt(i, j)).append("\t");
            }
            s.append("\n");
        }

        System.out.println(s.toString());
    }

    public void printVector(Double[] x)
    {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < x.length; i++)
        {
            s.append("x").append(i).append(" = ").append(x[i]).append("   ");
        }
        System.out.println(s);
    }
}
