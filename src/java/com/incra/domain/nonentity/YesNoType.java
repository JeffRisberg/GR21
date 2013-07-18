package com.incra.domain.nonentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The <i>YesNoType</i> enumeration is used on the filter screens for true/false
 * values.
 * 
 * @author Jeff Risberg
 * @since January 2011
 */
public enum YesNoType {
  YES("Yes"),
  NO("No");

  protected String label;

  /** Constructor */
  private YesNoType(String label) {
    this.label = label;

    ObjectRepo.shared_objectList.add(this);
    ObjectRepo.shared_keyToObjectMap.put(this.name(), this);
  }

  public String getLabel() {
    return label;
  }

  static public List<YesNoType> selectAll() {
    return ObjectRepo.shared_objectList;
  }

  static public YesNoType findByKey(String key) {
    return ObjectRepo.shared_keyToObjectMap.get(key);
  }

  static protected class ObjectRepo {
    static protected Map<String, YesNoType> shared_keyToObjectMap = new HashMap<String, YesNoType>();
    static protected List<YesNoType> shared_objectList = new ArrayList<YesNoType>();
  }
}