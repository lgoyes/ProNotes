package com.lamadridblandongoyes.data.wrappers

interface IEncoderWrapper<DecodedType, EncodedType> {
    fun map(input: DecodedType): EncodedType
}