/**
* Copyright 2014-2015 Smartsheet.com, Inc.
* 
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
*/

/**
 * Simple two-tuple.
 * Code from:  http://stackoverflow.com/a/3646398/2946799
 * 
 * Public immutable fields, i.e. simple data structure.
 * Comparable.
 * Simple hash and equals.
 * Simple factory so you don't have to provide the types:
 *    e.g.:  Pair.of("hello", 1);
 */
package com.smartsheet.tin.filters.common;

public class Pair<FIRST, SECOND>  implements Comparable<Pair<FIRST, SECOND>> {
	public final FIRST first;
	public final SECOND second;

	private Pair(FIRST first, SECOND second) {
		this.first = first;
		this.second = second;
	}

	public static <FIRST, SECOND> Pair<FIRST, SECOND> of(FIRST first,
			SECOND second) {
		return new Pair<FIRST, SECOND>(first, second);
	}

	@Override
	public int compareTo(Pair<FIRST, SECOND> o) {
		int cmp = compare(first, o.first);
		return cmp == 0 ? compare(second, o.second) : cmp;
	}

	// todo move this to a helper class.
	private static int compare(Object o1, Object o2) {
		return o1 == null ? o2 == null ? 0 : -1 : o2 == null ? +1
				: ((Comparable<Object>) o1).compareTo(o2);
	}

	@Override
	public int hashCode() {
		return 31 * hashcode(first) + hashcode(second);
	}

	// todo move this to a helper class.
	private static int hashcode(Object o) {
		return o == null ? 0 : o.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Pair))
			return false;
		if (this == obj)
			return true;
		return equal(first, ((Pair) obj).first)
				&& equal(second, ((Pair) obj).second);
	}

	// todo move this to a helper class.
	private boolean equal(Object o1, Object o2) {
		return o1 == null ? o2 == null : (o1 == o2 || o1.equals(o2));
	}

	@Override
	public String toString() {
		return "(" + first + ", " + second + ')';
	}
}
