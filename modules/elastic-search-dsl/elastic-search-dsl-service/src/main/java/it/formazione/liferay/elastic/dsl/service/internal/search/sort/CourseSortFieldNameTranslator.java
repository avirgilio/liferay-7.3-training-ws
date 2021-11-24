package it.formazione.liferay.elastic.dsl.service.internal.search.sort;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.contributor.constants.ContributorConstants;
import com.liferay.portal.search.contributor.sort.SortFieldNameTranslator;
import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = ContributorConstants.ENTRY_CLASS_NAME_PROPERTY_KEY + "=it.formazione.liferay.elastic.dsl.model.Course",
	service = SortFieldNameTranslator.class
)
public class CourseSortFieldNameTranslator implements SortFieldNameTranslator {
	@Override
	public String getSortFieldName(String orderByCol) {

		return Field.getSortableFieldName(
			StringBundler.concat(orderByCol, StringPool.UNDERLINE, "String"));
	}
}
