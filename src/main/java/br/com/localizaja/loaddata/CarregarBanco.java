package br.com.localizaja.loaddata;

import br.com.localizaja.dto.EstabelecimentoDTO;
import br.com.localizaja.dto.GeoLocation;
import br.com.localizaja.servico.Servico;
import br.com.localizaja.util.EntityConverter;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author adriana
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CarregarBanco {

    private static final Servico SERVICO = new Servico();
    private static final String PATH_XLS = "C:\\Users\\Daniel\\Desktop\\carga.xlsx";

    @PersistenceContext(name = "localizaja")
    private EntityManager entityManager;

    public CarregarBanco() {
    }

    public void carrega() throws Exception {

        System.out.println("Iniciando");

        try (FileInputStream arquivo = new FileInputStream(new File(PATH_XLS))) {

            System.out.println("Carregou arquivo");

            Workbook workbook = WorkbookFactory.create(arquivo);
            Sheet sheetAlunos = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheetAlunos.iterator();
            while (rowIterator.hasNext()) {

                System.out.println("Iniciando linha");

                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                EstabelecimentoDTO e = new EstabelecimentoDTO();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getColumnIndex()) {
                        case 0:
                            e.setNomeEmpresa(cell.getStringCellValue());
                            break;
                        case 1:
                            e.setTelefone(cell.getStringCellValue());
                            break;
                        case 2:
                            GeoLocation coordenadasGeograficas = SERVICO.getCoordenadasGeograficas(cell.getStringCellValue()).get(0);
                            e.setEnderecoCompleto(coordenadasGeograficas.getEnderecoCompleto());
                            e.setLatitude(e.getLatitude());
                            e.setLongitude(e.getLongitude());
                            System.out.println("Endereço recuperado");
                            break;
                        case 3:
                            e.setCategoria(cell.getStringCellValue());
                            break;
                    }
                }
                System.out.println("Iniciando Persistencia");
                entityManager.persist(EntityConverter.toEstabelecimento(e));
                System.out.println("REGISTRO INSERIDO!");
            }
            System.out.println("TERMINADO!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("FIM DA CARGA!");
    }
}
