
/*
 * Created on 2021-01-24 ( Time 21:50:27 )
 * Generator tool : Telosys Tools Generator ( version 3.1.2 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package ci.palmafrique.palm.utils.contract;

import lombok.*;

/**
 * Search Param
 * 
 * @author Geo
 *
 */
@Data
@ToString
@NoArgsConstructor
public class SearchParam<T> {

	String	operator;
	T		start;
	T		end;
}
