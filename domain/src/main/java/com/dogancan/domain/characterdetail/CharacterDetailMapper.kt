package com.dogancan.domain.characterdetail

import com.dogancan.domain.Mapper
import com.dogancan.domain.character.CharacterUiModel
import com.dogancan.responsemodel.ResultsItem
import javax.inject.Inject

/**
 * @author dogancankilic
 * Created at 5.06.2022
 */
class CharacterDetailMapper @Inject constructor() :
    Mapper<ResultsItem, CharacterUiModel> {
    override fun map(input: ResultsItem): CharacterUiModel {
        return CharacterUiModel(input.id, input.name, input.image)
    }
}
