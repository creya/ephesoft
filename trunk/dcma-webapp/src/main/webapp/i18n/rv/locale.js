﻿﻿﻿﻿﻿﻿﻿/**
	 * ******************************* Default locale
	 * **************************************************
	 */
var rvConstants = {
	rv_title : "Review and Validate Document",
	tabLabel_home : "Home",
	tabLabel_batch_detail : "Batch Detail",
	tabLabel_web_scanner : "Web Scanner",
	tabLabel_upload_batch: "Upload Batch",
	title_revVal_backButton : "Save and Show Batch List",
	title_revVal_nextButton : "Save and Show Next Batch",
	title_document : "DOCUMENT-",
	title_select_doc : "Select Document",
	title_merge_confirm : "Merge Confirmation",
	tooltip_split : "Split(Ctrl + t)",
	tooltip_delete : "Delete(Shift + DEL)",
	tooltip_duplicate : "Duplicate(Ctrl + d)",
	tooltip_move : "Move(Ctrl + m)",
	tooltip_zoom_in : "Zoom-in(Ctrl + 1)",
	tooltip_zoom_out : "Zoom-out(Ctrl + 2)",
	tooltip_fit_to_page : "Fit-to-page(F12)",
	tooltip_rotate : "Rotate(Ctrl + r)",
	title_duplicate : "Duplicate Page",
	title_delete : "Delete Page",
	title_split : "Split Document Confirmation",
	title_split_doc : "Split Document",
	title_topPanel_batchId : "BatchId",
	title_topPanel_batchClass : "BatchClass",
// title_topPanel_batch_status : "BatchStatus",
	title_topPanel_back : "Back to Batch List",
	title_topPanel_next : "Next Batch",
	title_topPanel_info : "Shortcuts",
	title_reviewPanel_docType : "Document Type",
	title_reviewPanel_mergeDocWith : "Merge Document with",
	title_confirmation_ok : "OK",
	title_confirmation_save : "Save",
	title_confirmation_discard : "Discard",
	title_confirmation_cancel : "Cancel",
	title_movePanel_page : "Page",
	title_movePanel_move_before : "Move Before",
	title_movePanel_move_after : "Move After",
	title_movePanel_move_page : "Move Page",
	title_movePanel_cancel_button : "Cancel",
	title_movePanel_document : "Document",
	/*
	 * batch_status_locked : "Locked", batch_status_ready : "Ready",
	 * batch_status_running : "Running", batch_status_readyForReview : "Ready
	 * For Review", batch_status_reviewed : "Reviewed",
	 * batch_status_readyForValidation : "Ready For Validation",
	 * batch_status_validated : "Validated", batch_status_error : "Error",
	 * batch_status_finished : "Finished",
	 */
	title_topPanel_batchClass : "BatchClass",
	info_save_document : "Save Document",
	info_split_document : "Split Document",
	info_zoom_in : "Zoom-in",
	info_zoom_out : "Zoom out",
	info_move_cursor_next_field : "Move cursor to next field",
	info_move_cursor_next_field_error : "Move cursor to next error field",
	info_move_cursor_previous_field_error : "Move cursor to previous error field",
	info_duplicate_page : "Duplicate Page",
	info_move_page : "Move page in document",
	info_fit_page : "Fit to Page",
	info_rotate_page : "Rotate Page",
	info_remove_page : "Remove Page",
	info_review_to_validate : "For Review to Validate",
	change_document_type : "Change document type",
	merge_document_to_previous_one : "Merge document to previous one",
	fuzzy_search_tooltip : "Fuzzy DB search",
	table_view_shortcut : "Table View",
	table_view_back_shortcut : "Back From Table View",
	fuzzy_search_go_btn : "Go",
	fuzzy_search_title : "Fuzzy DB Search result",
	fuzzy_search_cancel_btn : "Cancel",
	fuzzy_search_select_btn : "Select",
	info_or : "or",
	info_delete : "Delete",
	info_title : "KeyBoard Shortcuts",
	document_type_unknown : "Unknown",
	msg_save_confirmation : "Save confirmation",
	msg_next : "Next",
	msg_back : "Back",
	title_move : "Move Page",
	alternate_value : "Alt Value",
	title_review_done : "Review Done",
	title_validation_done : "Validation Done",
	title_table_view_tooltip : "Table View",
	regex_retrieval_fail : "Regex Pattern",
	delete_row_button : "Delete",
	insert_row_button : "Insert",
	table_insert_row_shortcut : "Insert row in table",
	table_delete_row_shortcut : "Delete row in table",
	traverse_table_shortcut : "Traverse Tables",
	ok_button : "OK",
	cancel_button : "Cancel",
	image_scroll_left_shortcut : "Move image left",
	image_scroll_right_shortcut : "Move image right",
	image_scroll_up_shortcut : "Move image up",
	image_scroll_down_shortcut : "Move image down",
	image_zoom_lock : "Lock/Unlock zoom",
	zoom_locked : "Zoom Locked",
	zoom_unlocked : "Zoom Unlocked",
	doc_type_toggle : "Toggle Document Types",
	next_doc_type : "Next Document",
	prev_doc_type : "Previous Document",
	next_page_type : "Next Page Within Same Document",
	prev_page_type : "Previous Page Within Same Document",
	unable_to_find : "Unable to find",
	delete_all_row_button : "Delete All",
	add_new_table_button : "Add New Table",
	add_new_table : "Add New Table",
	table_delete_all_row_shortcut : "Delete All Rows in table",
	add_new_table_fail : "Add New Table",
	msg_next_batch : "Next Batch",
	manual_extraction : "Manual extraction",
	start_extraction : "Start Extraction",
	manual_table_extraction_shortcut : "Insert data manually in table / Start extraction from table",
	name_of_app_shortcut_ctrl_4 : "App1",
	name_of_app_shortcut_ctrl_7 : "App2",
	name_of_app_shortcut_ctrl_8 : "App3",
	name_of_app_shortcut_ctrl_9 : "App4",
	modal_window_shortcuts : "Modal Window Shortcuts",
	function_key_shortcuts : "Function Key Shortcuts",
	title_topPanel_batchName : "BatchName",
	disclosure_panel_shortcut : "Review Panel Open or Close",
	review_panel : "Review Panel",
	save_button_tooltip : "Save(Ctrl + s)",
	title_topPanel_save_anchor : "Save Batch",
	regex_activate_deactivate_toggle_shortcut: "Regex Activate/Deactivate"
};

var rvMessages = {
	msg_userName_error : "Unable to retrieve the username",
	msg_backButton_confm : "You are navigating away from this page. Any unsaved data will be lost, do you want to save it or discard it?",
	error_topPanel_ok_failure : "Error saving batch {0}. {1}",
	error_topPanel_ok_success : "Unable to release lock on batch- {0}",
	error_topPanel_noMoreBatches : "No more batches available",
	error_topPanel_lock_acquire : "Could not acquire lock on batch- {0} Exception-> {1}",
	error_ret_next_batch : "Error retrieving next batch",
	error_batch_already_locked : "Batch- {0} is already locked by some other user",
	error_ret_batch : "Error retrieving a Batch for processing",
	error_ret_specific_batch : "Error retrieving Batch with BatchId: {0}. It is not of status READY_FOR_REVIEW or READY_FOR_VALIDATION.",
	error_save_batch : "Unable to save batch: {0}",
	msg_review_confirm : "Review of the batch is done. Please press OK to process another batch.",
	msg_validation_confirm : "Validation of the batch is done.Please press OK to process another batch.",
	msg_tree_merge_doc : "Do you want to Merge Document- {0} into Document- {1} ?",
	msg_tree_merge_doc_failure : "Merging of Document{0} into Document{1}  Failed. Exception-> {2}",
	msg_overlayPanel_duplicate : "Do you want to Duplicate the page {0} ?",
	msg_overlayPanel_duplicate_error : "Unable to duplicate page-{0}",
	msg_overlayPanel_delete : "Do you want to delete the page {0} ?",
	msg_overlayPanel_delete_error : "Unable to delete page-{0} Exception-> {1}",
	msg_overlayPanel_split_fst_page : "You can not split a document from first page.",
	msg_overlayPanel_split : "Do you want to split the document {0} at page {1} ?",
	msg_fuzzy_search_unsuccessful : "Unsuccessful Fuzzy DB search.",
	msg_fuzzy_search_no_result : "No result found for Fuzzy DB search.",
	msg_fuzzy_search_invalid_entry : "Invalid entry for Fuzzy DB search.",
	msg_overlayPanel_split_error : "Unable to split document- {0} at page- {1} Exception-> {2}",
	msg_overlayPanel_rotate_error : "Failed to rotate Image.",
	msg_movePanel_move_error : "Unable to move page-{0} to document-{1} Exception-> {2}",
	msg_overlayPanel_last_page_delete_error : "Cannot delete the last page of the last document.",
	regex_retrieval_fail : "Unable to apply any regex pattern on table.",
	delete_row_confirmation : "Are you sure you want to delete this row.",
	delete_row_title : "Delete Row",
	no_data_to_delete : "No data in table to delete",
	no_row_selected : "No row selected",
	no_data_to_display : "No data to display",
	insert_row : "Insert row",
	insert_before : "Insert Before",
	insert_after : "Insert After",
	no_radio_button_selected_error : "No radio button selected.",
	regex_pattern_compliant : "should be compliant with the pattern",
	zoom_lock_success : "Scroll locked successfully",
	zoom_unlock_success : "Scroll unlocked successfully",
	default_doc_type_view_failure : "Failed to retrieve default document type view",
	document_type_error : "No such document type exists",
	error_batch_status : "The batch {0} is not of status READY_FOR_REVIEW or READY_FOR_VALIDATION. Cannot open this batch.",
	none_selected : "",
	error_value : "Error",
	delete_all_row_confirmation : "Are you sure you want to delete all rows of selected table.",
	delete_all_row_title : "Delete all rows",
	add_new_table_fail : "Unable to Add New Table.",
	first_complete_manual_extraction : "First Complete Manual Extraction for {0}",
	columns_overlapping_error : "Selected column overlaps with another selected column.",
	invalid_row_selected_for_manual_extarction : "First Complete Manual Extraction for row {0} of table {1}",
	different_row_error : "All selected values should be in same row.",
	unable_to_fetch_data : "Unable to fetch table data.",
	no_column_selected : "No Column Selected.",
	exit_manual_extracion : "Are you sure you want to exit the Manual Extraction for {0}?",
	invalid_table : "Invalid data in {0}",
	unable_to_fetch_batch_xml : "Unable to fetch updated batch.xml for the current batch.",
	unable_to_fetch_external_app_info : "Unable to fetch information for the configuration of external applications.",
	unable_to_retrieve_field_value_script_switch_state : "Unable to fetch field value script switch state.",
	unable_to_display_external_application : "Unable to display external application.",
	unable_to_create_authentication_for_external_application : "Unable to create authentication mechanism for external application.",
	unable_to_execute_script_and_save : "Unable to save changes due to  failure of script execution on field value change.",
	script_execution_error : "Script execution error",
	select_document_type : "\"Unknown\" document type selected.\nPlease select some other document type.",
	unable_to_get_zoom_count : "Unable to get zoom count value from properties file",
	session_time_out : "Your session has timed out.",
	unable_to_acquire_lock : "Batch is locked by some other user for editing.",
	no_right_to_open_batch : "You do not have the rights to open this batch."

};

/** ************************** Turkish locale (suffix: _tk)****************** */
var rvConstants_tk = {
	rv_title : "Inceleme ve Belge Doğrula",
	tabLabel_home : "Ev",
	tabLabel_batch_detail : "İş Detayı",
	tabLabel_web_scanner : "Web Tarayıcı",
	tabLabel_upload_batch: "Toplu Yükle",
	title_revVal_backButton : "Kaydet ve İş Listesini Göster",
	title_revVal_nextButton : "Kaydet ve Baska İş Göster",
	title_document : "Döküman-",
	title_select_doc : "Döküman seç",
	title_merge_confirm : "Birleştirme Onayı",
	tooltip_split : "Böl(Ctrl + t)",
	tooltip_delete : "Sil(Shift + DEL)",
	tooltip_duplicate : "Kopya(Ctrl + d)",
	tooltip_move : "Taşı(Ctrl + m)",
	tooltip_zoom_in : "Yakinlaştır(Ctrl + 1)",
	tooltip_zoom_out : "Uzaklaştır(Ctrl + 2)",
	tooltip_fit_to_page : "Sayfaya sığdır(F12)",
	tooltip_rotate : "Döndür(Ctrl + r)",
	title_duplicate : "Sayfayı Kopyala",
	title_delete : "Sayfayı Sil",
	title_split : "Döküman Bölme Onayı",
	title_split_doc : "Döküman Bölme",
	title_topPanel_batchId : "İşNo",
	title_topPanel_batchClass : "İş Akışı",
// title_topPanel_batch_status : "İşDurum",
	title_reviewPanel_docType : "Döküman Tipi",
	title_reviewPanel_mergeDocWith : "Döküman Birleştirme",
	title_topPanel_back : "Geri Toplu Listesine",
	title_topPanel_next : "Sonraki Toplu",
	title_topPanel_info : "Kilitli",
	title_confirmation_ok : "Tamam",
	title_confirmation_cancel : "Iptal",
	title_confirmation_save : "Kaydetmek",
	title_confirmation_discard : "Atmak",
	title_movePanel_page : "Sayfa",
	title_movePanel_move_before : "Önce Taşı",
	title_movePanel_move_after : "Sonra taşı",
	title_movePanel_move_page : "Sayfa Taşı",
	title_movePanel_cancel_button : "Iptal",
	title_movePanel_document : "Belge",
	/*
	 * batch_status_locked : "Kilitli", batch_status_ready : "Hazır",
	 * batch_status_running : "Koşma", batch_status_readyForReview : "Gözden
	 * Geçirme İçin Hazır", batch_status_reviewed : "İncelendi",
	 * batch_status_readyForValidation : "Doğrulama İçin Hazır",
	 * batch_status_validated : "Valide", batch_status_error : "Hata",
	 * batch_status_finished : "Bitmiş",
	 */
	info_save_document : "Kaydet Belge",
	info_split_document : "Split Belge",
	info_zoom_in : "Yakınlaştırmak",
	info_zoom_out : "Uzaklaştır",
	info_move_cursor_next_field : "bir sonraki alana götür",
	info_move_cursor_next_field_error : "sonraki hata alan götür",
	info_move_cursor_previous_field_error : "Önceki hata alana götür",
	info_duplicate_page : "Yinelenen Sayfa",
	info_move_page : "Belgedeki sayfa Taşı",
	info_fit_page : "uygun sayfa",
	info_rotate_page : "Döndür Sayfa",
	info_remove_page : "Kaldır Sayfa",
	info_review_to_validate : "Doğrulamak için inceleme için",
	change_document_type : "Değişim belge türü",
	merge_document_to_previous_one : "Bir önceki belge birleştirme",
	fuzzy_search_tooltip : "Bulanık DB arama",
	table_view_shortcut : "Masa Profili",
	table_view_back_shortcut : "Masa Profili Back From",
	fuzzy_search_go_btn : "Gitmek",
	fuzzy_search_title : "Bulanık DB Arama sonucu",
	fuzzy_search_cancel_btn : "Iptal",
	fuzzy_search_select_btn : "Seçin",
	info_or : "veya",
	info_delete : "Silmek",
	info_title : "Klavye Kısayolları",
	document_type_unknown : "Bilinmeyen",
	msg_save_confirmation : "Kaydet onay",
	msg_next : "Sonraki",
	msg_back : "Geri",
	title_move : "Hareket Sayfa",
	alternate_value : "Alt deger",
	title_review_done : "Inceleme Bitti",
	title_validation_done : "Doğrulama Tamamlandı",
	title_table_view_tooltip : "Masa Profili",
	regex_retrieval_fail : "Regex Desen",
	delete_row_button : "Silmek",
	insert_row_button : "Eklemek",
	table_insert_row_shortcut : "Tabloda satır ekle",
	table_delete_row_shortcut : "Tabloda Satır sil",
	traverse_table_shortcut : "Çapraz Tablolar",
	ok_button : "Tamam",
	cancel_button : "Iptal",
	image_scroll_left_shortcut : "Görüntü sola taşı",
	image_scroll_right_shortcut : "Görüntü sağa taşı",
	image_scroll_up_shortcut : "Hareket görüntüsü",
	image_scroll_down_shortcut : "Hareket resmi küçültmek",
	doc_type_toggle : "Geçiş Belge Türleri",
	next_doc_type : "Sonraki Belge",
	prev_doc_type : "Önceki Belge",
	next_page_type : "Sonraki Sayfa Aynı belge içinde",
	prev_page_type : "Önceki Sayfa Aynı belge içinde",
	unable_to_find : "Kurulamıyor bulundu",
	delete_all_row_button : "Tümünü Sil",
	add_new_table_button : "Yeni Tablo Ekle",
	add_new_table : "Eklemek Yeni Tablo",
	table_delete_all_row_shortcut : "Tabloda Tümünü Sil Satırlar",
	add_new_table_fail : "Yeni Tablo Ekle",
	msg_next_batch : "Sonraki Toplu",
	manual_extraction : "manuel çıkarma",
	start_extraction : "Ekstraksiyon Başlat",
	manual_table_extraction_shortcut : "Masadan masaya Başlat / çıkarma el ile veri ekleme",
	name_of_app_shortcut_ctrl_4 : "App1",
	name_of_app_shortcut_ctrl_7 : "App2",
	name_of_app_shortcut_ctrl_8 : "App3",
	name_of_app_shortcut_ctrl_9 : "App4",
	modal_window_shortcuts : "Kalıcı Window Kısayolları",
	function_key_shortcuts : "İşlev Tuşu Kısayolları",
	title_topPanel_batchName : "Toplu iş adı",
	disclosure_panel_shortcut : "Gözden Geçirme Paneli Aç veya Kapat",
	review_panel :	"Gözden Geçirme Paneli",
	image_zoom_lock : "Kilitleme / Kilidini zum",
	zoom_locked : "Yakınlaştırma Kilitli",
	zoom_unlocked : "Yakınlaştırma kilidi",
	save_button_tooltip : "Sakla(Ctrl + s)",
	title_topPanel_save_anchor : "Sakla Toplu",
	regex_activate_deactivate_toggle_shortcut: "Düzenli ifade aktif / deaktif"
};

var rvMessages_tk = {
	msg_userName_error : "kullanıcı adı alınamadı",
	msg_backButton_confm : "Sen Toplu özellikleri değişmiştir, bunu kaydetmek veya silmek istersiniz?",
	error_topPanel_ok_failure : "{0} İşni kaydederken İstisna Oluştu-> {1}",
	error_topPanel_ok_success : "{0} İş kilidi serbest Bırakılamadı",
	error_topPanel_noMoreBatches : "Başka İş Bulunamadı",
	error_topPanel_lock_acquire : "{0} nolu İş Kilitlenemedi-> {1}",
	error_ret_next_batch : "Sonraki işi alırken Hata olustu",
	error_batch_already_locked : "İş- {0} başka bir kullanıcı tarafından kilitlenmiştir",
	error_ret_batch : "Hata işleme için Toplu almak",
	error_ret_specific_batch : "BatchId ile Toplu alınırken hata: {0}. Bu durum READY_FOR_REVIEW veya READY_FOR_VALIDATION sayılmazdı.",
	error_save_batch : "toplu kaydedilemiyor: {0}",
	msg_review_confirm : "Toplu İnceleme yapılır. Başka bir toplu işlemek için OK tuşuna basınız.",
	msg_validation_confirm : "Toplu Doğrulama başka bir toplu işlemek için OK tuşuna basın done.Please olduğunu.",
	msg_tree_merge_doc : "olmak ister misiniz Merge Belge {0} içine Belge {1}?",
	msg_tree_merge_doc_failure : "Doküman {0} Belge içine birleştirme {1} Başarısız. İstisna-> {2}",
	msg_overlayPanel_duplicate : "Eğer Duplicate istiyor musunuz sayfa {0}?",
	msg_overlayPanel_duplicate_error : "Yapamaz çoğaltmak sayfa {0}",
	msg_overlayPanel_delete : "Silmek istiyor musunuz sayfa {0}?",
	msg_overlayPanel_delete_error : "Yapamaz silmek için sayfa {0} Exception-> {1}",
	msg_overlayPanel_split_fst_page : "Birinci sayfadan bir D\u00F6k\u00FCman? b\u00F6lemezsiniz.",
	msg_overlayPanel_split : "Eğer sayfada belge {0} bölmek istiyor musunuz {1}?",
	msg_fuzzy_search_unsuccessful : "Başarısız Bulanık DB arama.",
	msg_fuzzy_search_no_result : "Hiçbir sonuç Bulanık DB arama bulundu.",
	msg_fuzzy_search_invalid_entry : "Bulanık DB arama için geçersiz girdi.",
	msg_overlayPanel_split_error : "split kurulamıyor belge-{0} olarak sayfa {1} Exception-> {2}",
	msg_overlayPanel_rotate_error : "Sayfa Döndürülürken hata olustu.",
	msg_movePanel_move_error : "Yapamaz taşımak için sayfa {0} belge {1} Exception-> {2}",
	msg_overlayPanel_last_page_delete_error : "son belgenin son sayfasına silinemiyor.",
	regex_retrieval_fail : "Masanın üzerinde hiç regex deseni uygulamak kurulamıyor.",
	delete_row_confirmation : "Eğer bu satırı silmek istediğinizden emin misiniz.",
	delete_row_title : "Satır silmek",
	no_data_to_delete : "Tabloda herhangi bir veri  silmek",
	no_row_selected : "Seçilmedi satır",
	no_data_to_display : "Hiçbir veri görüntülemek için",
	insert_row : "Satır",
	insert_before : "Takmadan önce",
	insert_after : "Ekledikten sonra",
	no_radio_button_selected_error : "HiÃ§bir radyo dÃ¼ÄŸmesini seÃ§ili.",
	regex_pattern_compliant : "desen ile uyumlu olmalÄ±dÄ±r",
	default_doc_type_view_failure : "VarsayÄ±lan belge tÃ¼rÃ¼nÃ¼ gÃ¶rÃ¼ntÃ¼lemek alÄ±namadÄ±",
	document_type_error : "BÃ¶yle bir belge tÃ¼rÃ¼ var",
	error_batch_status : "Toplu iş {0} durumu READY_FOR_REVIEW veya READY_FOR_VALIDATION sayılmazdı. Bu toplu açılamıyor.",
	none_selected : "",
	error_value : "Hata",
	delete_all_row_confirmation : "Seçtiğiniz tablonun tüm satırları silmek istediğinizden emin misiniz.",
	delete_all_row_title : "Silmek tüm satırları",
	add_new_table_fail : "Yeni Tablo Ekle kurulamıyor",
	first_complete_manual_extraction : "Için ilk komple Kılavuzu çıkarımı {0}",
	columns_overlapping_error : "Başka bir seçili sütun sütun örtüşmektedir Seçilen.",
	invalid_row_selected_for_manual_extarction : "Tablo {1} satır {0} için ilk komple Kılavuzu Ekstraksiyon",
	different_row_error : "Seçilen tüm değerler aynı sırada olmalıdır.",
	unable_to_fetch_data : "Tablo verilerini alamıyor.",
	no_column_selected : "Hayır Sütun seçildi.",
	exit_manual_extracion : "{0} Manuel Ekstraksiyon çıkmak istediğinizden emin misiniz?",
	invalid_table : "Geçersiz veri {0}",
	unable_to_fetch_batch_xml : "Mevcut toplu güncellenen batch.xml alınamıyor.",
	unable_to_fetch_external_app_info : "Harici uygulamaların, yapılandırma için bilgi alınamıyor.",
	unable_to_retrieve_field_value_script_switch_state : "Alan değeri komut dosyası anahtarı devlet alınamıyor.",
	unable_to_display_external_application : "Dış uygulama görüntülemek için açılamıyor.",
	unable_to_create_authentication_for_external_application : "Dış uygulama için kimlik doğrulama mekanizması oluşturulamadı.",
	unable_to_execute_script_and_save : "Alan değerini değiştirmek betik çalıştırma hatası nedeniyle değişiklikler kaydedilemiyor.",
	script_execution_error : "Komut yürütme hatası",
	select_document_type : "\"Bilinmeyen\"belge türü seçilir.\nBaşka bir belge türü seçin.",
	unable_to_get_zoom_count : "Kıtlıklarını karşılamak zum kont değer özellikleri dosya.",
	session_time_out : "Oturumunuz zaman aşımına uğradı.",
	unable_to_acquire_lock : "Toplu düzenleme için başka bir kullanıcı tarafından kilitlenmiş durumda.",
	no_right_to_open_batch : "Bu toplu iş açmak için haklara sahip değilsiniz."
};