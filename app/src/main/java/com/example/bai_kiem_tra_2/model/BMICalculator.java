package com.example.bai_kiem_tra_2.model;

public class BMICalculator {

    // Calculate BMI
    public static float calculateBMI(float weight, float height) {
        return weight / (height * height);
    }

    // Get health status based on BMI
    public static String getHealthStatus(float bmi) {
        if (bmi < 18.5) {
            return "Thiếu cân";
        } else if (bmi < 25) {
            return "Bình thường";
        } else if (bmi < 30) {
            return "Thừa cân";
        } else {
            return "Béo phì";
        }
    }

    // Get detailed health warning based on BMI
    public static String getHealthWarning(float bmi) {
        if (bmi < 18.5) {
            return "Bạn đang trong tình trạng thiếu cân. Hãy tăng cường dinh dưỡng và tập luyện để cải thiện sức khỏe.";
        } else if (bmi < 25) {
            return "Chỉ số BMI của bạn ở mức bình thường. Hãy duy trì chế độ ăn uống và luyện tập hiện tại.";
        } else if (bmi < 30) {
            return "Bạn đang trong tình trạng thừa cân. Hãy chú ý đến chế độ ăn uống và tăng cường vận động.";
        } else {
            return "Bạn đang trong tình trạng béo phì. Hãy tham khảo ý kiến bác sĩ để có kế hoạch giảm cân phù hợp.";
        }
    }

    // Calculate ideal weight range based on height and gender
    public static String getIdealWeightRange(float height, String gender) {
        float lowerBMI = 18.5f;
        float upperBMI = 24.9f;

        float lowerWeight = lowerBMI * height * height;
        float upperWeight = upperBMI * height * height;

        // Adjust for gender (women typically have lower ideal weights)
        if (gender.equalsIgnoreCase("Nữ")) {
            lowerWeight *= 0.95;
            upperWeight *= 0.95;
        }

        return String.format("%.1f kg - %.1f kg", lowerWeight, upperWeight);
    }
}