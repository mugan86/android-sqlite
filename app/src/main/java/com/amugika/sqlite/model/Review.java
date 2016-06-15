package com.amugika.sqlite.model;

import java.math.BigDecimal;
import java.util.ArrayList;

/********************************************
 * Created by anartzmugika on 15/6/16.
 */
public class Review {

    private String avg_points;
    private ArrayList<Valoration> valorations;

    public Review(ArrayList<Valoration> valorations, float avg_points_float)
    {
        this.valorations = valorations;
        setAvg_pointsRounded(avg_points_float);
    }

    public Review(ArrayList<Valoration> valorations)
    {
        this.valorations = valorations;
        set_Avg_with_valorations();
    }


    public String getAvg_points() {
        return avg_points;
    }

    public void setAvg_pointsRounded(float avg_points) {
        //this.avg_points = avg_points;
        BigDecimal bd = new BigDecimal(Float.toString(avg_points));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.avg_points = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    public void setValorations(ArrayList<Valoration> valorations) {
        this.valorations = valorations;
    }

    public int getValorationBarWidth(int counts)
    {
        int width = 25;
        if (counts > 0 && counts < 5)
        {
            width = 50;
        }
        else if (counts >= 5 && counts < 10)
        {
            width = 75;
        }
        else if (counts >= 10 && counts < 15)
        {
            width = 100;
        }
        else if (counts >= 15 && counts < 20)
        {
            width = 125;
        }
        else if (counts >= 20 && counts < 25)
        {
            width = 150;
        }
        else if (counts >= 25 && counts < 30)
        {
            width = 175;
        }
        else if (counts >= 30 && counts < 35)
        {
            width = 200;
        }

        else if (counts >= 35 && counts < 40)
        {
            width = 225;
        }
        else if (counts >= 40 && counts < 45)
        {
            width = 250;
        }
        else if (counts >= 45 && counts < 50)
        {
            width = 275;
        }
        else if (counts >= 55 && counts < 100)
        {
            width = 300;
        }
        else if (counts >= 100)
        {
            width = 375;
        }
        return width;
    }

    public int getValorationsCount()
    {
        return this.valorations.size();
    }


    public int[][] getValuesCount()
    {
        int [][] valorations_array = new int[5][2];
        for (int i = 0; i < valorations.size(); i++)
        {
            Valoration valoration = valorations.get(i);
            float val = 0;
            try
            {
                val = valoration.getValue();
            }
            catch(Exception e)
            {
                val = 0;
            }


            if (val < 1.0)
            {
                valorations_array[4][0] = 5 - i;
                valorations_array[4][1] = valorations_array[4][1] + 1;
            }
            else if (val >= 1.0 && val < 2.0)
            {
                valorations_array[3][0] = 5 - i;
                valorations_array[3][1] = valorations_array[3][1] + 1;
            }
            else if (val >= 2.0 && val < 3.0)
            {
                valorations_array[2][0] = 5 - i;
                valorations_array[2][1] = valorations_array[2][1] + 1;
            }
            else if (val >= 3.0 && val < 4.0)
            {
                valorations_array[1][0] = 5 - i;
                valorations_array[1][1] = valorations_array[1][1] + 1;
            }
            else if (val >= 4.0)
            {
                valorations_array[0][0] = 5 - i ;
                valorations_array[0][1] = valorations_array[0][1] + 1;
            }
        }
        return valorations_array;
    }

    public void set_Avg_with_valorations()
    {
        float value_count = 0;
        for (int i = 0; i < valorations.size(); i++)
        {
            value_count = value_count + valorations.get(i).getValue();
        }
        setAvg_pointsRounded(value_count/getValorationsCount());
    }
}
