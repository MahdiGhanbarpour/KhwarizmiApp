package ir.mahdighanbarpour.khwarazmiapp.features.loginScreen

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColorStateList
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import ir.mahdighanbarpour.khwarazmiapp.R
import ir.mahdighanbarpour.khwarazmiapp.databinding.FragmentLoginMainBinding
import ir.mahdighanbarpour.khwarazmiapp.utils.SEND_SELECTED_ROLE_TO_LOGIN_OTP_FRAGMENT_KEY
import ir.mahdighanbarpour.khwarazmiapp.utils.STUDENT
import ir.mahdighanbarpour.khwarazmiapp.utils.TEACHER

class LoginMainFragment : Fragment() {

    private lateinit var binding: FragmentLoginMainBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Apply focus on the Text Input Layout
        binding.etLayoutNumLogin.requestFocus()

        navController = Navigation.findNavController(view)

        listener()
    }

    private fun listener() {
        binding.radioGroupRoleLogin.radioGroupRole.setOnCheckedChangeListener { _, id ->

            // Change the Input Layouts hint based on the user's selected role
            when (id) {
                R.id.radioBtStudent -> {
                    setSelectedRoleColor(
                        STUDENT,
                        "شماره تلفن همراه دانش آموز",
                        R.color.blue,
                        R.color.blue,
                        R.color.white,
                        R.drawable.shape_role_radio_button_background,
                        R.drawable.selector_role_radio_button_background
                    )
                }

                R.id.radioBtTeacher -> {
                    setSelectedRoleColor(
                        TEACHER,
                        "شماره تلفن همراه دبیر",
                        R.color.teacher_color,
                        R.color.white,
                        R.color.teacher_color,
                        R.drawable.shape_role_radio_button_background_teacher,
                        R.drawable.selector_role_radio_button_background_teacher
                    )
                }

                else -> {
                    setSelectedRoleColor(
                        STUDENT,
                        "شماره تلفن همراه دانش آموز",
                        R.color.blue,
                        R.color.blue,
                        R.color.white,
                        R.drawable.shape_role_radio_button_background,
                        R.drawable.selector_role_radio_button_background
                    )
                }
            }
        }
    }

    // Checking the validity of the entered phone number
    fun checkInput() {
        val enteredNum = binding.etNumLogin.text.toString()

        if (enteredNum.length != 11) {
            binding.etLayoutNumLogin.error = "شماره تلفن همراه معتبر نیست"
        } else if (!enteredNum.startsWith("09")) {
            binding.etLayoutNumLogin.error = "شماره تلفن همراه می‌بایست با 09 اغاز شود"
        } else {
            val roleBundle = Bundle()
            roleBundle.putString(
                SEND_SELECTED_ROLE_TO_LOGIN_OTP_FRAGMENT_KEY,
                if (binding.radioGroupRoleLogin.radioBtStudent.isChecked) STUDENT else TEACHER
            )

            navController.navigate(
                R.id.action_loginMainFragment_to_loginOtpFragment, roleBundle
            )

            binding.etLayoutNumLogin.error = null
        }
    }

    private fun setSelectedRoleColor(
        selectedRole: String,
        hintText: String,
        mainColor: Int,
        btTeacherColor: Int,
        btStudentColor: Int,
        shapeBG: Int,
        selectorBG: Int
    ) {
        binding.etLayoutNumLogin.hint = hintText

        binding.etLayoutNumLogin.boxStrokeColor =
            ContextCompat.getColor(requireContext(), mainColor)

        if (Build.VERSION.SDK_INT >= 23) {
            binding.etLayoutNumLogin.defaultHintTextColor =
                resources.getColorStateList(mainColor, requireActivity().theme)
        } else {
            binding.etLayoutNumLogin.defaultHintTextColor =
                getColorStateList(requireContext(), mainColor)
        }


        binding.radioGroupRoleLogin.radioGroupRole.background = ContextCompat.getDrawable(
            requireContext(), shapeBG
        )
        binding.radioGroupRoleLogin.radioBtTeacher.background = ContextCompat.getDrawable(
            requireContext(), selectorBG
        )
        binding.radioGroupRoleLogin.radioBtStudent.background = ContextCompat.getDrawable(
            requireContext(), selectorBG
        )


        binding.radioGroupRoleLogin.radioBtStudent.setTextColor(
            ContextCompat.getColor(
                requireContext(), btStudentColor
            )
        )
        binding.radioGroupRoleLogin.radioBtTeacher.setTextColor(
            ContextCompat.getColor(
                requireContext(), btTeacherColor
            )
        )

        (requireActivity() as LoginActivity).changeAppColor(selectedRole)
    }
}