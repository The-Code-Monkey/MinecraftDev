/*
 * Minecraft Dev for IntelliJ
 *
 * https://minecraftdev.org
 *
 * Copyright (c) 2017 minecraft-dev
 *
 * MIT License
 */

package com.demonwav.mcdev.platform.canary.framework

import com.demonwav.mcdev.asset.PlatformAssets
import com.intellij.framework.library.LibraryVersionProperties
import com.intellij.openapi.roots.libraries.LibraryPresentationProvider
import com.intellij.openapi.roots.libraries.LibraryProperties
import com.intellij.openapi.util.io.JarUtil
import com.intellij.openapi.vfs.VfsUtilCore
import com.intellij.openapi.vfs.VirtualFile
import java.util.jar.Attributes

class CanaryPresentationProvider : LibraryPresentationProvider<LibraryVersionProperties>(CANARY_LIBRARY_KIND) {

    override fun getIcon(properties: LibraryProperties<*>?) = PlatformAssets.CANARY_ICON

    override fun detect(classesRoots: List<VirtualFile>) =
        classesRoots.asSequence()
            .map { VfsUtilCore.virtualToIoFile(it) }
            .filter { JarUtil.getJarAttribute(it, Attributes.Name.IMPLEMENTATION_TITLE) == "CanaryLib" }
            .mapNotNull { JarUtil.getJarAttribute(it, Attributes.Name.IMPLEMENTATION_VERSION) }
            .map(::LibraryVersionProperties)
            .firstOrNull()
}
