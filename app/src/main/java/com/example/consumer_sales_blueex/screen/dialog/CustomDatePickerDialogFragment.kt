import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.DialogFragment
import com.archit.calendardaterangepicker.customviews.CalendarListener
import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView
import com.example.consumer_sales_blueex.R
import com.example.consumer_sales_blueex.screen.ktx.convertDate
import java.util.Calendar

class CustomDatePickerDialogFragment : DialogFragment() {

    private var cStartDate = ""
    private var cEndDate = ""

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_date_picker_dialog)

        val datePicker = dialog.findViewById<DateRangeCalendarView>(R.id.datePicker)
        val btConfirmDate = dialog.findViewById<Button>(R.id.btSelectDate)



        btConfirmDate.setOnClickListener {

            val result = Bundle().apply {
                putString("cStartDate", cStartDate)
                putString("cEndDate", cEndDate)
            }
            parentFragmentManager.setFragmentResult("datePickerRequest", result)
            dismiss()
        }

        return dialog
    }
}
