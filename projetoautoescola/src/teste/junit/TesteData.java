package teste.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Test;

import br.com.project.report.util.DateUtils;

public class TesteData {
	@Test
	public void mostraData() {
		try {
			String data = DateUtils.getDateAtualReportName();
			System.out.println(data);
			assertEquals("'2019-09-09'", DateUtils.formatDateSQL(Calendar.getInstance().getTime()));

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
