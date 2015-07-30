package cl.kleedy.sbif.api

import cl.kleedy.sbif.api.indicadores.UF

/**
 * Operaciones con la unidad de fomento
 * Created by josebovet on 7/30/15.
 */
interface UFOperations {

    /***
     * Permite obtener el valor de la UF para el día actual.
     * @return
     * @throws SBIFClientException
     */
    def UF getUF() throws SBIFClientException;

    /***
     * Permite obtener un listado con el valor de la UF para cada día del año que se indique.
     * @param year
     * @return
     * @throws SBIFClientException
     */
    def List<UF> getUFByYear(int year) throws SBIFClientException;

    /***
     * Permite obtener un listado con el valor de la UF para cada día del mes y año que se indique.
     * @param year
     * @param month
     * @return
     * @throws SBIFClientException
     */
    def List<UF> getUFByYear(int year, int month) throws SBIFClientException;

    /***
     * Permite obtener el valor de la UF para el día del mes y año que se indique.
     * @param year
     * @param month
     * @param day
     * @return
     * @throws SBIFClientException
     */
    def UF getUFByYear(int year, int month, int day) throws SBIFClientException;

    /***
     * Permite obtener un listado con el valor de la UF para una fecha posterior al año que se indique.
     * @param year
     * @return
     * @throws SBIFClientException
     */
    def List<UF> getUFLaterYear(int year) throws SBIFClientException;

    /***
     * Permite obtener un listado con el valor de la UF para una fecha posterior al mes y año que se indique.
     * @param year
     * @param month
     * @return
     * @throws SBIFClientException
     */
    def List<UF> getUFLaterYear(int year, int month) throws SBIFClientException;

    /***
     * Permite obtener el valor de la UF para una fecha posterior al año que se indique.
     * @param year
     * @param month
     * @param day
     * @return UF
     * @throws SBIFClientException
     */
    def UF getUFLaterYear(int year, int month, int day) throws SBIFClientException;
}
