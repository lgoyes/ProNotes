package com.lamadridblandongoyes.data.wrappers

interface IDecoderWrapper<EncodedType, DecodedType> {
    fun invert(input: EncodedType): DecodedType
}