package com.example.data.dto

object DummyDto {
    data class Fortune(
        val id : Int,
        val fortune : String,
        val anything : String
    )
    val fortuneData = listOf(
        Fortune(1,"오늘은 행운이 가득한 날입니다. 새로운 기회를 잡아보세요!","aa"),
        Fortune(2,"신중한 결정이 필요한 하루입니다. 너무 서두르지 마세요.","bb"),
        Fortune(3,"작은 일에도 감사하는 마음을 가지면 더 큰 행운이 따라올 것입니다.","cc"),
        Fortune(4,"오늘은 좋은 기운이 가득합니다. 새로운 도전을 해보세요!","dd"),
        Fortune(5,"작은 일에 연연하지 말고 큰 그림을 보는 것이 중요합니다.","ee"),
        Fortune(6,"오늘은 여유를 가지고 주변을 돌아보세요. 뜻밖의 행운이 있을지도 몰라요!","ff"),
        Fortune(7,"계획한 일이 있다면 과감하게 실행에 옮겨보세요. 좋은 결과가 따를 것입니다.","gg"),
        Fortune(8,"조금은 쉬어가는 것도 필요합니다. 에너지를 충전하세요.","hh"),
        Fortune(9,"어려움이 있어도 포기하지 마세요. 곧 좋은 일이 찾아옵니다.","ii"),
        Fortune(10,"당신의 아이디어가 빛을 발할 날이 곧 올 것입니다. 포기하지 마세요.","jj"),
    )
}