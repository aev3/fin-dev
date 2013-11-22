/*
 *
 */

package com.awolart.fin.cu.ps4;

/*
 * Abstract super class for all lattices.
 */
abstract public class AbstractLattice
{

    protected double[][] lattice;

    protected int n;

    public AbstractLattice(int n)
    {
        lattice = new double[n + 1][n + 1];
        this.n = n;
    }

    public double getCell(int row, int col)
    {
        return lattice[row][col];
    }

    protected abstract void createLattice();

    protected void prettyPrint()
    {
        int size = this.lattice[0].length - 1;
        for(int i = 0; i <= size; i++)
        {
            for(int j = 0; j <= size; j++)
            {
                System.out.printf("%8.4f\t", this.lattice[i][j]);
            }

            System.out.println();
        }

        System.out.println();
    }
}
