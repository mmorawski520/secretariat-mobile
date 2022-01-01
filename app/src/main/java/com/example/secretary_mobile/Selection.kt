package com.example.secretary_mobile

import android.widget.EditText
import android.widget.Spinner

class Selection {
    companion object {
        fun chooseSelection(
            youngerThan: String?,
            olderThan: String?,
            editTextField: EditText,
            spinnerSearchField: Spinner,
            spinnerOrderByAscDesc: Spinner,
            curTable: String,
            spinnerOrderByField: Spinner
        ): String {
            var select = ""
            if (youngerThan == "" && olderThan == "") {
                if (editTextField.text.toString().trim().isNotEmpty()) {
                    if (spinnerSearchField.selectedItem.toString() != null && spinnerSearchField.selectedItem.toString() != "") {
                        if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                            select =
                                "SELECT * FROM " + curTable + " WHERE " + spinnerSearchField.selectedItem.toString() + " LIKE '%" + editTextField.text.toString() + "%' ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();
                        } else {
                            select =
                                "SELECT * FROM " + curTable + " WHERE " + spinnerSearchField.selectedItem.toString() + " LIKE '%" + editTextField.text.toString() + "%'";
                        }
                    } else {
                        if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                            select =
                                "SELECT * FROM " + curTable + " ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();
                        } else {
                            select = "SELECT * FROM " + curTable;
                        }
                    }
                } else {
                    if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                        select =
                            "SELECT * FROM " + curTable + " ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();
                    } else {
                        select = "SELECT * FROM " + curTable;
                    }
                }
            }
            if (youngerThan != "" && olderThan == "") {
                if (editTextField.text.toString().trim().isNotEmpty()) {
                    if (spinnerSearchField.selectedItem.toString() != null && spinnerSearchField.selectedItem.toString() != "") {
                        if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                            select =
                                "SELECT * FROM " + curTable + " WHERE " + spinnerSearchField.selectedItem.toString() + " LIKE '%" + editTextField.text.toString() + "%' AND substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birh_date,1,2) < substr('" + youngerThan + "',7,4) || substr('" + youngerThan + "',4,2) || substr('" + youngerThan + "',1,2) ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();
                        } else {
                            select =
                                "SELECT * FROM " + curTable + " WHERE " + spinnerSearchField.selectedItem.toString() + " LIKE '%" + editTextField.text.toString() + "%' AND  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birh_date,1,2) < substr('" + youngerThan + "',7,4) || substr('" + youngerThan + "',4,2) || substr('" + youngerThan + "',1,2) ";
                        }
                    } else {
                        if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                            select =
                                "SELECT * FROM " + curTable + " WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) < substr('" + youngerThan + "',7,4) || substr('" + youngerThan + "' ,4,2) || substr('" + youngerThan + "',1,2)  ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();
                        } else {
                            select =
                                "SELECT * FROM " + curTable + " WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) < substr('" + youngerThan + "',7,4) || substr('" + youngerThan + "',4,2) || substr('" + youngerThan + "',1,2) ";
                        }
                    }
                } else {
                    if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                        select =
                            "SELECT * FROM " + curTable + " WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) < substr('" + youngerThan + "',7,4) || substr('" + youngerThan + "',4,2) || substr('" + youngerThan + "',1,2)  ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();

                    } else {
                        select =
                            "SELECT * FROM " + curTable + " WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) < substr('" + youngerThan + "',7,4) || substr('" + youngerThan + "',4,2) || substr('" + youngerThan + "',1,2) ";
                    }
                }
            }
            if (youngerThan == "" && olderThan != "") {
                if (editTextField.text.toString().trim().isNotEmpty()) {
                    if (spinnerSearchField.selectedItem.toString() != null && spinnerSearchField.selectedItem.toString() != "") {
                        if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                            select =
                                "SELECT * FROM " + curTable + " WHERE " + spinnerSearchField.selectedItem.toString() + " LIKE '%" + editTextField.text.toString() + "%' AND substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birh_date,1,2) > substr('" + olderThan + "',7,4) || substr('" + olderThan + "',4,2) || substr('" + olderThan + "',1,2) ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();

                        } else {
                            select =
                                "SELECT * FROM " + curTable + " WHERE " + spinnerSearchField.selectedItem.toString() + " LIKE '%" + editTextField.text.toString() + "%' AND  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birh_date,1,2) > substr('" + olderThan + "',7,4) || substr('" + olderThan + "',4,2) || substr('" + olderThan + "',1,2) ";
                        }
                    } else {
                        if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                            select =
                                "SELECT * FROM " + curTable + " WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) > substr('" + olderThan + "',7,4) || substr('" + olderThan + "' ,4,2) || substr('" + olderThan + "',1,2)  ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();
                        } else {
                            select =
                                "SELECT * FROM " + curTable + " WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) > substr('" + olderThan + "',7,4) || substr('" + olderThan + "',4,2) || substr('" + olderThan + "',1,2) ";
                        }
                    }
                } else {
                    if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                        select =
                            "SELECT * FROM " + curTable + " WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) > substr('" + olderThan + "',7,4) || substr('" + olderThan + "',4,2) || substr('" + olderThan + "',1,2)  ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();

                    } else {
                        select =
                            "SELECT * FROM " + curTable + " WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) > substr('" + olderThan + "',7,4) || substr('" + olderThan + "',4,2) || substr('" + olderThan + "',1,2) ";
                    }
                }
            }
            if (youngerThan != "" && olderThan != "") {
                if (editTextField.text.toString().trim().isNotEmpty()) {
                    if (spinnerSearchField.selectedItem.toString() != null && spinnerSearchField.selectedItem.toString() != "") {
                        if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                            select =
                                "SELECT * FROM " + curTable + " WHERE " + spinnerSearchField.selectedItem.toString() + " LIKE '%" + editTextField.text.toString() + "%' AND substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birh_date,1,2) > substr('" + olderThan + "',7,4) || substr('" + olderThan + "',4,2) || substr('" + olderThan + "',1,2) ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();

                        } else {
                            select =
                                "SELECT * FROM " + curTable + " WHERE " + spinnerSearchField.selectedItem.toString() + " LIKE '%" + editTextField.text.toString() + "%' AND  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birh_date,1,2) > substr('" + olderThan + "',7,4) || substr('" + olderThan + "',4,2) || substr('" + olderThan + "',1,2) ";
                        }
                    } else {
                        if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                            select =
                                "SELECT * FROM " + curTable + " WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) > substr('" + olderThan + "',7,4) || substr('" + olderThan + "' ,4,2) || substr('" + olderThan + "',1,2) AND  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birh_date,1,2) < substr('" + youngerThan + "',7,4) || substr('" + youngerThan + "',4,2) || substr('" + youngerThan + "',1,2) ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();
                        } else {
                            select =
                                "SELECT * FROM " + curTable + " WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) > substr('" + olderThan + "',7,4) || substr('" + olderThan + "',4,2) || substr('" + olderThan + "',1,2) AND  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birh_date,1,2) < substr('" + youngerThan + "',7,4) || substr('" + youngerThan + "',4,2) || substr('" + youngerThan + "',1,2)";
                        }
                    }
                } else {
                    if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                        select =
                            "SELECT * FROM " + curTable + " WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) > substr('" + olderThan + "',7,4) || substr('" + olderThan + "',4,2) || substr('" + olderThan + "',1,2) AND  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birh_date,1,2) < substr('" + youngerThan + "',7,4) || substr('" + youngerThan + "',4,2) || substr('" + youngerThan + "',1,2) ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();

                    } else {
                        select =
                            "SELECT * FROM " + curTable + " WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) > substr('" + olderThan + "',7,4) || substr('" + olderThan + "',4,2) || substr('" + olderThan + "',1,2) AND  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birh_date,1,2) < substr('" + youngerThan + "',7,4) || substr('" + youngerThan + "',4,2) || substr('" + youngerThan + "',1,2) ";
                    }
                }
            }
            return select
        }
    }
}
