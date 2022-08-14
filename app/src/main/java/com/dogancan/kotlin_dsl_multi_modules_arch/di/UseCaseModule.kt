package com.dogancan.kotlin_dsl_multi_modules_arch.di

import com.dogancan.domain.character.CharacterUseCase
import com.dogancan.domain.character.ICharacterUseCase
import com.dogancan.domain.characterdetail.CharacterDetailUseCase
import com.dogancan.domain.characterdetail.ICharacterDetailUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * @author dogancankilic
 * Created on 8.08.2022
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindCharacterDetailUseCase(impl: CharacterDetailUseCase): ICharacterDetailUseCase

    @Binds
    abstract fun bindCharacterUseCase(impl: CharacterUseCase): ICharacterUseCase
}
