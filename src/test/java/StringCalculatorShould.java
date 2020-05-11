import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorShould {
    /*

    TO DO LIST
    1º "" -> 0
    2º "1" -> 1
    3º "3,2" -> 5

     */
    @Test
    public void Devolver_0_en_caso_de_vacio(){

        assertThat(StringCalculator.add("")).isEqualTo("0");

    }

    @Test
    public void Devolver_numero_en_caso_de_encontrar_dicho_numero(){
        assertThat(StringCalculator.add("1.1")).isEqualTo("1.1");
        assertThat(StringCalculator.add("1")).isEqualTo("1");
        assertThat(StringCalculator.add("44")).isEqualTo("44");
    }


    @Test
    public void Devolver_suma_en_caso_de_mas_de_un_numero_separado_por_coma(){
        assertThat(StringCalculator.add("2, 2,2.2")).isEqualTo("6.2");
        assertThat(StringCalculator.add("2,3")).isEqualTo("5");
    }

    @Test
    public void Devolver_suma_con_salto_de_linea(){
        assertThat(StringCalculator.add("2.2,2")).isEqualTo("4.2");
        assertThat(StringCalculator.add("2\n3")).isEqualTo("5");
    }

    @Test
    public void Devolver_error_en_caso_de_comas_de_mas(){
        assertThat(StringCalculator.add("2,,2,")).isEqualTo("Number expected but EOF found.");
        assertThat(StringCalculator.add(",2,3,")).isEqualTo("Number expected but EOF found.");
    }

    @Test
    public void Devolver_error_en_caso_de_numero_negativo(){
        assertThat(StringCalculator.add("2,-2")).isEqualTo("Negative not allowed: -2");
        assertThat(StringCalculator.add("-12\n-3,-2")).isEqualTo("Negative not allowed: -12-3-2");
    }

}
