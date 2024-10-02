package net.hwyz.iov.mp.app.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.placeholder.material.placeholder
import net.hwyz.iov.mp.app.R
import net.hwyz.iov.mp.app.theme.AppTheme
import net.hwyz.iov.mp.app.theme.SearchBarHeight
import net.hwyz.iov.mp.app.component.item.TextContentItem
import net.hwyz.iov.mp.app.component.title.MiniTitle


data class TabTitle(
    val id: Int,
    val text: String,
    var cachePosition: Int = 0,
    var selected: Boolean = false
)

/**
 * TabLayout
 */
@Composable
fun TextTabBar(
    index: Int,
    tabTexts: List<TabTitle>,
    modifier: Modifier = Modifier,
    contentAlign: Alignment = Alignment.Center,
    bgColor: Color = AppTheme.colors.themeUi,
    contentColor: Color = Color.White,
    onTabSelected: ((index: Int) -> Unit)? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(bgColor)
            .horizontalScroll(state = rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .align(contentAlign)
        ) {
            tabTexts.forEachIndexed { i, tabTitle ->
                Text(
                    text = tabTitle.text,
                    fontSize = if (index == i) 20.sp else 15.sp,
                    fontWeight = if (index == i) FontWeight.SemiBold else FontWeight.Normal,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 10.dp)
                        .clickable {
                            onTabSelected?.invoke(i)
                        },
                    color = contentColor
                )
            }
        }
    }
}

@Composable
fun HomeSearchBar(
    onSearchClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(SearchBarHeight)
            .background(color = AppTheme.colors.themeUi)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .height(30.dp)
                .align(alignment = Alignment.Top)
                .weight(1f)
                .background(
                    color = AppTheme.colors.background,
                    shape = RoundedCornerShape(12.5.dp)
                )
                .clickable { onSearchClick() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_search),
                contentDescription = "搜索",
                tint = AppTheme.colors.themeUi,
                modifier = Modifier
                    .size(25.dp)
                    .padding(start = 10.dp)
                    .align(Alignment.CenterVertically)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = "搜索关键词以空格形式隔开",
                    fontSize = 13.sp,
                    color = AppTheme.colors.fontSecondary
                )
            }
        }
    }
}

@Composable
fun EmptyView(
    tips: String = "啥都没有~",
    imageVector: ImageVector = Icons.Default.Info,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize(1f)
            .defaultMinSize(minHeight = 480.dp)
    ) {
        Column(
            Modifier
                .wrapContentSize()
                .align(Alignment.Center)
                .clickable { onClick.invoke() }
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                tint = AppTheme.colors.fontSecondary,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            TextContentItem(text = tips, modifier = Modifier.padding(top = 10.dp))
        }
    }
}

@Composable
fun TagView(
    modifier: Modifier = Modifier,
    tagText: String,
    tagBgColor: Color = AppTheme.colors.background,
    borderColor: Color = AppTheme.colors.themeUi,
    tagTextColor: Color = AppTheme.colors.fontSecondary,
    isLoading: Boolean = false,
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .background(color = tagBgColor)
            .clip(RoundedCornerShape(2.dp))
            .border(width = 1.dp, color = borderColor)
            .placeholder(
                visible = isLoading,
                color = AppTheme.colors.fontSecondary
            )
    ) {
        MiniTitle(
            text = tagText,
            color = tagTextColor,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 5.dp, vertical = 0.dp),
        )
    }
}