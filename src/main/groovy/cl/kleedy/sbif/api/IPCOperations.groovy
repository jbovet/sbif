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

import cl.kleedy.sbif.api.indicadores.IPC

/**
 * Operaciones Indice de Precios al Consumidor
 * Created by josebovet on 7/15/15.
 */
interface IPCOperations {

    /***
     * Permite obtener el valor de el IPC para el mes actual.
     * @return
     */
    def IPC getIPC() throws SBIFClientException;

    /***
     * Permite obtener un listado con el valor de el IPC para cada mes del año que se indique.
     * @param year
     * @return
     */
    def List<IPC> getIPCByYear(int year) throws SBIFClientException;

    /***
     * Permite obtener un listado con el valor de el IPC de cada mes correspondiente
     * a una fecha posterior al año que se indique.
     * @param year
     * @return
     */
    def List<IPC> getIPCLaterYear(int year) throws SBIFClientException;

    /***
     * Permite obtener un listado con el valor de el IPC de cada mes correspondiente
     * a una fecha posterior al mes y año que se indique.
     * @param year
     * @param month
     * @return
     */
    def List<IPC> getIPCByLaterYearAndMonth(int year, int month) throws SBIFClientException;

    /***
     * Permite obtener un listado con el valor de el IPC de cada mes correspondiente
     * a las fechas anteriores al año que se indique.
     * @param year
     * @return
     */
    def List<IPC> getIPCByPreviousYear(int year) throws SBIFClientException;

    /***
     * Permite obtener un listado con el valor de el IPC de cada mes correspondiente
     * a las fechas anteriores al año y mes que se indique.
     * @param year
     * @param month
     * @return
     */
    def List<IPC> getIPCByPreviousYearAndMonth(int year, int month) throws SBIFClientException;

    /***
     * Permite obtener un listado con el valor de el IPC para los meses incluidos dentro de los parámetros
     * @param year
     * @param month
     * @return List <IPC>
     */
    def List<IPC> getIPCByPeriodYearAndMonth(int year, int month, int year2, int month2) throws SBIFClientException;

    /***
     * Permite obtener un listado con el valor de el IPC para cada uno de los meses incluidos
     * dentro de los años que se indiquen.
     * @param year
     * @param year2
     * @return
     */
    def List<IPC> getIPCPeriodos(int year, int year2) throws SBIFClientException;


}
