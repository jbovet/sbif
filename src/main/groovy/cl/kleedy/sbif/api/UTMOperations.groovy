/*
 *
 *  * SBIF Client: Java/Groovy API client implementation for
 *    Superintendencia de Bancos e Instituciones Financieras de Chile
 *  * Copyright (C) 2015  Jose P. Bovet Derpich (jose.bovet@gmail.com)
 *  *
 *  * This program is free software; you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation; either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *  *
 *
 */

package cl.kleedy.sbif.api

import cl.kleedy.sbif.api.indicadores.UTM

/**
 * Operaciones con Unidad Tributaria Mensual
 * Created by josebovet on 7/15/15.
 */
interface UTMOperations {

    /***
     * Permite obtener el valor de la UTM para el mes actual.
     * @return
     */
    def UTM getUTM();

    /***
     * Permite obtener un listado con el valor de la UTM para
     * cada mes del año que se indique.
     * @param year
     * @return
     */
    def List<UTM> getUTMByYear(int year);

    /***
     * Permite obtener un listado con el valor de la UTM para el mes y año que se indique
     * @param year
     * @param month
     * @return
     */
    def List<UTM> getUTMByYearAndMonth(int year, int month);

    /****
     * Permite obtener un listado con el valor de la UTM de cada mes correspondiente
     * a una fecha posterior a la que se indique.
     * @param year
     * @return
     */
    def List<UTM> getUTMLaterYear(int year);

    /****
     * Permite obtener un listado con el valor de la UTM de cada mes correspondiente a
     * una fecha posterior al mes y año que se indique en los parámetros.
     * @param year
     * @param month
     * @return
     */
    def List<UTM> getUTMLaterYearAndMonth(int year, int month);
}
